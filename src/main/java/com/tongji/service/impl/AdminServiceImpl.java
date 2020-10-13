package com.tongji.service.impl;

import com.tongji.common.utils.JwtTokenUtil;
import com.tongji.dao.AdminRoleDao;
import com.tongji.mbg.mapper.AdminMapper;
import com.tongji.mbg.model.Admin;
import com.tongji.mbg.model.AdminExample;
import com.tongji.mbg.model.Permission;
import com.tongji.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * AdminService实现类
 */
@Service
public class AdminServiceImpl implements AdminService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminRoleDao adminRoleDao;

    @Override
    public Admin getAdminByUsername(String username)
    {
        AdminExample example = new AdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<Admin> adminList = adminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0)
        {
            return adminList.get(0);
        }
        return null;
    }

    @Override
    public Admin register(Admin AdminParam)
    {
        Admin Admin = new Admin();
        BeanUtils.copyProperties(AdminParam, Admin);
        Admin.setCreateTime(new Date());
        Admin.setStatus(1);
        //查询是否有相同用户名的用户
        AdminExample example = new AdminExample();
        example.createCriteria().andUsernameEqualTo(Admin.getUsername());
        List<Admin> AdminList = adminMapper.selectByExample(example);
        if (AdminList.size() > 0)
        {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(Admin.getPassword());
        Admin.setPassword(encodePassword);
        adminMapper.insert(Admin);
        return Admin;
    }

    @Override
    public String login(String username, String password)
    {
        String token = null;
        try
        {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword()))
            {
                throw new BadCredentialsException("密码不正确");
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


    @Override
    public List<Permission> getPermissionList(Integer adminId)
    {
        return adminRoleDao.getPermissionList(adminId);
    }
}
