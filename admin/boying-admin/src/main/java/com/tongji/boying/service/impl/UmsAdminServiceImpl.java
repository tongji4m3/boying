package com.tongji.boying.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.config.AdminUserDetails;
import com.tongji.boying.dao.UmsAdminRoleDao;
import com.tongji.boying.dto.UmsAdminInfoParam;
import com.tongji.boying.dto.UmsAdminRegisterParam;
import com.tongji.boying.mapper.AdminUserMapper;
import com.tongji.boying.mapper.AdminRoleMapper;
import com.tongji.boying.mapper.AdminUserRoleMapper;
import com.tongji.boying.model.*;
import com.tongji.boying.security.util.JwtTokenUtil;
import com.tongji.boying.service.UmsAdminCacheService;
import com.tongji.boying.service.UmsAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * UmsAdminService实现类
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdminUserMapper adminUserMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;
    @Autowired
    private AdminUserRoleMapper adminUserRoleMapper;
    @Autowired
    private UmsAdminCacheService adminCacheService;
    @Autowired
    private UmsAdminRoleDao adminRoleDao;


    @Override
    public AdminUser getAdminByUsername(String username)
    {
        AdminUser adminUser = adminCacheService.getAdmin(username);
        if (adminUser != null) return adminUser;
        AdminUserExample example = new AdminUserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<AdminUser> adminList = adminUserMapper.selectByExample(example);
//        下面语句等价与:if (adminList!=null && adminList.size() > 0)
        if (CollUtil.isNotEmpty(adminList))
        {
            adminUser = adminList.get(0);
            adminCacheService.setAdmin(adminUser);
            return adminUser;
        }
        return null;
    }

    @Override
    public AdminUser getCurrentAdmin()
    {
        //        获取之前登录存储的用户上下文信息
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        AdminUserDetails userDetails = (AdminUserDetails) auth.getPrincipal();
        return userDetails.getAdmin();
    }

    @Override
    public AdminUser register(UmsAdminRegisterParam param)
    {
//        封装参数
        AdminUser AdminUser = new AdminUser();
        BeanUtils.copyProperties(param, AdminUser);
        AdminUser.setCreateTime(new Date());
        AdminUser.setStatus(true);

        //查询是否有相同用户名的用户
        AdminUserExample example = new AdminUserExample();
        example.createCriteria().andUsernameEqualTo(AdminUser.getUsername());
        List<AdminUser> umsAdminList = adminUserMapper.selectByExample(example);
        if (umsAdminList.size() > 0)
        {
            Asserts.fail("注册失败,管理员名称重复!");
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(AdminUser.getPassword());
        AdminUser.setPassword(encodePassword);
        adminUserMapper.insertSelective(AdminUser);
        return AdminUser;
    }

    @Override
    public String login(String username, String password)
    {
        String token = null;
        //密码需要客户端加密后传递
        try
        {
            UserDetails userDetails = loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword()))
            {
                Asserts.fail("密码不正确");
            }
            if (!userDetails.isEnabled())
            {
                Asserts.fail("帐号已被禁用");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            //修改登录时间
            AdminUser currentAdmin = getCurrentAdmin();
            currentAdmin.setLoginTime(new Date());
            adminUserMapper.updateByPrimaryKey(currentAdmin);
        } catch (AuthenticationException e)
        {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }


    /**
     * 根据用户名修改登录时间
     */
    private void updateLoginTimeByUsername(String username)
    {
        AdminUser record = new AdminUser();
        record.setLoginTime(new Date());
        AdminUserExample example = new AdminUserExample();
        example.createCriteria().andUsernameEqualTo(username);
        adminUserMapper.updateByExampleSelective(record, example);
    }

    @Override
    public String refreshToken(String oldToken)
    {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }

    @Override
    public AdminUser getItem(Integer id)
    {
        return adminUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<AdminUser> list(String keyword, Integer pageSize, Integer pageNum)
    {
        PageHelper.startPage(pageNum, pageSize);
        AdminUserExample example = new AdminUserExample();
        AdminUserExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword))
        {
            criteria.andUsernameLike("%" + keyword + "%");
        }
        return adminUserMapper.selectByExample(example);
    }

    @Override
    public int update(Integer id, UmsAdminInfoParam param)
    {
        AdminUser AdminUser = new AdminUser();
        BeanUtils.copyProperties(param, AdminUser);
        AdminUser.setId(id);
        int count = adminUserMapper.updateByPrimaryKeySelective(AdminUser);
        adminCacheService.delAdmin(id);
        return count;
    }

    @Override
    public int delete(Integer id)
    {
        if (id.equals(getCurrentAdmin().getId()))//当删除的账号为当前账号时不允许删除
        {
            return -1;
        }
        adminCacheService.delAdmin(id);
        int count = adminUserMapper.deleteByPrimaryKey(id);
        adminCacheService.delResourceList(id);
        return count;
    }

    @Override
    public int updateRole(Integer userId, List<Integer> roleIds)
    {
        if (adminUserMapper.selectByPrimaryKey(userId) == null) Asserts.fail("该管理员不存在!");
        if (roleIds == null) Asserts.fail("请传入要分配的角色Id列表!");

        int count = roleIds.size();
        //先删除原来的关系
        AdminUserRoleExample adminUserRoleExample = new AdminUserRoleExample();
        adminUserRoleExample.createCriteria().andUserIdEqualTo(userId);
        //该角色对应的管理员人数-1
        List<AdminUserRole> adminUserRoles = adminUserRoleMapper.selectByExample(adminUserRoleExample);
        System.out.println("adminUserRoles");
        System.out.println(adminUserRoles.size());
        for (AdminUserRole adminUserRole : adminUserRoles)
        {
            System.out.println(adminUserRole.getRoleId());
            AdminRole role = new AdminRole();
            role.setId(adminUserRole.getRoleId());
            role.setAdminCount(adminRoleMapper.selectByPrimaryKey(adminUserRole.getRoleId()).getAdminCount() - 1);
            adminRoleMapper.updateByPrimaryKeySelective(role);
        }
        adminUserRoleMapper.deleteByExample(adminUserRoleExample);

        System.out.println(roleIds);
        //如果不分配角色，直接返回
        if (roleIds.size() == 0)
        {
            return 1;
        }
        //判断角色是否都是存在与数据库中的
        AdminRoleExample roleExample = new AdminRoleExample();
        roleExample.createCriteria().andIdIn(roleIds);
        if (roleIds.size() != adminRoleMapper.selectByExample(roleExample).size())
        {
            Asserts.fail("某些分配的角色Id不合法!");
        }

        System.out.println("123");
        //建立新关系
        if (!CollectionUtils.isEmpty(roleIds))
        {

            List<AdminUserRole> list = new ArrayList<>();
            for (Integer roleId : roleIds)
            {
                AdminUserRole adminRole = new AdminUserRole();
                adminRole.setUserId(userId);
                adminRole.setRoleId(roleId);
                list.add(adminRole);
                //对应角色管理员人数+1
                AdminRole AdminRole = new AdminRole();
                AdminRole.setId(roleId);
                AdminRole.setAdminCount(adminRoleMapper.selectByPrimaryKey(roleId).getAdminCount() + 1);
                adminRoleMapper.updateByPrimaryKeySelective(AdminRole);
            }
            System.out.println("456");
            adminRoleDao.insertList(list);
            System.out.println("789");
        }
        adminCacheService.delResourceList(userId);
        return count;
    }

    @Override
    public List<AdminRole> getRoleList(Integer adminId)
    {
        if (adminUserMapper.selectByPrimaryKey(adminId) == null) Asserts.fail("该管理员不存在!");
        return adminRoleDao.getRoleList(adminId);
    }

    @Override
    public List<AdminResource> getResourceList(Integer adminId)
    {
        List<AdminResource> resourceList = adminCacheService.getResourceList(adminId);
        if (CollUtil.isNotEmpty(resourceList))
        {
            return resourceList;
        }
        resourceList = adminRoleDao.getResourceList(adminId);
        if (CollUtil.isNotEmpty(resourceList))
        {
            adminCacheService.setResourceList(adminId, resourceList);
        }
        return resourceList;
    }


    @Override
    public UserDetails loadUserByUsername(String username)
    {
        //获取用户信息
        AdminUser AdminUser = getAdminByUsername(username);
        if (AdminUser != null)
        {
            List<AdminResource> resourceList = getResourceList(AdminUser.getId());
            return new AdminUserDetails(AdminUser, resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public int updatePassword(String username, String newPassword)
    {
        if (StrUtil.isEmpty(username)
                || StrUtil.isEmpty(newPassword))
        {
            return -1;
        }
        AdminUserExample example = new AdminUserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<AdminUser> adminList = adminUserMapper.selectByExample(example);
        if (CollUtil.isEmpty(adminList))
        {
            return -2;
        }
        AdminUser AdminUser = adminList.get(0);
        AdminUser.setPassword(passwordEncoder.encode(newPassword));
        adminUserMapper.updateByPrimaryKey(AdminUser);
        adminCacheService.delAdmin(AdminUser.getId());
        return 1;
    }

    @Override
    public int updatePassword(String username, String password, String newPassword)
    {
        if (StrUtil.isEmpty(username)
                || StrUtil.isEmpty(password)
                || StrUtil.isEmpty(newPassword))
        {
            return -1;
        }
        AdminUserExample example = new AdminUserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<AdminUser> adminList = adminUserMapper.selectByExample(example);
        if (CollUtil.isEmpty(adminList))
        {
            return -2;
        }
        AdminUser AdminUser = adminList.get(0);
        if (!passwordEncoder.matches(password, AdminUser.getPassword()))
        {
            return -3;
        }
        AdminUser.setPassword(passwordEncoder.encode(newPassword));
        adminUserMapper.updateByPrimaryKey(AdminUser);
        adminCacheService.delAdmin(AdminUser.getId());
        return 1;
    }

    @Override
    public int deleteRole(Integer adminId, List<Integer> roleIds)
    {
        if (adminUserMapper.selectByPrimaryKey(adminId) == null) Asserts.fail("该管理员不存在!");
        if (roleIds == null) Asserts.fail("请传入要删除的角色Id列表!");

        int count = roleIds.size();
        //先删除原来的关系
        AdminRoleExample adminRoleExample = new AdminRoleExample();
        adminRoleExample.createCriteria().andIdEqualTo(adminId).andIdIn(roleIds);
        List<AdminRole> adminRoles = adminRoleMapper.selectByExample(adminRoleExample);
        //不是全部合法
        if (roleIds.size() > adminRoles.size())
        {
            Asserts.fail("要删除的角色不是全部都存在!");
        }
        //该角色对应的管理员人数-1
        for (AdminRole adminRole : adminRoles)
        {
            AdminRole AdminRole = new AdminRole();
            AdminRole.setId(adminRole.getId());
            AdminRole.setAdminCount(adminRoleMapper.selectByPrimaryKey(adminRole.getId()).getAdminCount() - 1);
            adminRoleMapper.updateByPrimaryKeySelective(AdminRole);
        }
        adminRoleMapper.deleteByExample(adminRoleExample);

        adminCacheService.delResourceList(adminId);
        return count;
    }
}
