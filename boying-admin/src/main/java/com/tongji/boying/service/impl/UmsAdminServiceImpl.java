package com.tongji.boying.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.config.AdminUserDetails;
import com.tongji.boying.dto.AdminInfoParam;
import com.tongji.boying.dto.AdminParam;
import com.tongji.boying.dto.AdminPasswordParam;
import com.tongji.boying.mapper.AdminMapper;
import com.tongji.boying.mapper.AdminRoleMapper;
import com.tongji.boying.model.*;
import com.tongji.boying.security.util.JwtTokenUtil;
import com.tongji.boying.service.UmsAdminCacheService;
import com.tongji.boying.service.UmsAdminService;
import com.tongji.boying.dao.UmsAdminRoleDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
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
    private AdminMapper adminMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;
    @Autowired
    private UmsAdminCacheService adminCacheService;

    @Autowired
    private UmsAdminRoleDao adminRoleDao;


    @Override
    public Admin getAdminByUsername(String username)
    {
        Admin admin = adminCacheService.getAdmin(username);
        if (admin != null) return admin;
        AdminExample example = new AdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<Admin> adminList = adminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0)
        {
            admin = adminList.get(0);
            adminCacheService.setAdmin(admin);
            return admin;
        }
        return null;
    }

    @Override
    public Admin register(AdminParam adminParam)
    {
        Admin admin = new Admin();
        BeanUtils.copyProperties(adminParam, admin);
        admin.setCreateTime(new Date());
        admin.setStatus(true);
        //查询是否有相同用户名的用户
        AdminExample example = new AdminExample();
        example.createCriteria().andUsernameEqualTo(admin.getUsername());
        List<Admin> umsAdminList = adminMapper.selectByExample(example);
        if (umsAdminList.size() > 0)
        {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(encodePassword);
        adminMapper.insert(admin);
        return admin;
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
        }
        catch (AuthenticationException e)
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
        Admin record = new Admin();
        record.setLoginTime(new Date());
        AdminExample example = new AdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        adminMapper.updateByExampleSelective(record, example);
    }

    @Override
    public String refreshToken(String oldToken)
    {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }

    @Override
    public Admin getItem(Integer id)
    {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Admin> list(String keyword, Integer pageSize, Integer pageNum)
    {
        PageHelper.startPage(pageNum, pageSize);
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword))
        {
            criteria.andUsernameLike("%" + keyword + "%");
        }
        return adminMapper.selectByExample(example);
    }

    @Override
    public int update(Integer id, AdminInfoParam param)
    {
        Admin admin = new Admin();
        BeanUtils.copyProperties(param, admin);
        admin.setAdminId(id);
        int count = adminMapper.updateByPrimaryKeySelective(admin);
        adminCacheService.delAdmin(id);
        return count;
    }

    @Override
    public int delete(Integer id)
    {
        adminCacheService.delAdmin(id);
        int count = adminMapper.deleteByPrimaryKey(id);
        adminCacheService.delResourceList(id);
        return count;
    }

    @Override
    public int updateRole(Integer adminId, List<Integer> roleIds)
    {
        int count = roleIds == null ? 0 : roleIds.size();
        //先删除原来的关系
        AdminRoleExample adminRoleExample = new AdminRoleExample();
        adminRoleExample.createCriteria().andAdminIdEqualTo(adminId);
        adminRoleMapper.deleteByExample(adminRoleExample);
        //建立新关系
        if (!CollectionUtils.isEmpty(roleIds))
        {
            List<AdminRole> list = new ArrayList<>();
            for (Integer roleId : roleIds)
            {
                AdminRole adminRole = new AdminRole();
                adminRole.setAdminId(adminId);
                adminRole.setRoleId(roleId);
                list.add(adminRole);
            }
            adminRoleDao.insertList(list);
        }
        adminCacheService.delResourceList(adminId);
        return count;
    }

    @Override
    public List<Role> getRoleList(Integer adminId)
    {
        return adminRoleDao.getRoleList(adminId);
    }

    @Override
    public List<Resource> getResourceList(Integer adminId)
    {
        List<Resource> resourceList = adminCacheService.getResourceList(adminId);
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
    public int updatePassword(AdminPasswordParam param)
    {
        if (StrUtil.isEmpty(param.getUsername())
                || StrUtil.isEmpty(param.getOldPassword())
                || StrUtil.isEmpty(param.getNewPassword()))
        {
            return -1;
        }
        AdminExample example = new AdminExample();
        example.createCriteria().andUsernameEqualTo(param.getUsername());
        List<Admin> adminList = adminMapper.selectByExample(example);
        if (CollUtil.isEmpty(adminList))
        {
            return -2;
        }
        Admin admin = adminList.get(0);
        if (!passwordEncoder.matches(param.getOldPassword(), admin.getPassword()))
        {
            return -3;
        }
        admin.setPassword(passwordEncoder.encode(param.getNewPassword()));
        adminMapper.updateByPrimaryKey(admin);
        adminCacheService.delAdmin(admin.getAdminId());
        return 1;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
    {
        //获取用户信息
        Admin admin = getAdminByUsername(username);
        if (admin != null)
        {
            List<Resource> resourceList = getResourceList(admin.getAdminId());
            return new AdminUserDetails(admin, resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }
}
