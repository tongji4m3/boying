package com.tongji.boying.service.impl;

import com.tongji.boying.bo.BoyingUserDetails;
import com.tongji.boying.mapper.UserMapper;
import com.tongji.boying.model.User;
import com.tongji.boying.model.UserExample;
import com.tongji.boying.security.util.JwtTokenUtil;
import com.tongji.boying.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
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

import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public User getByUsername(String username)
    {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<User> userList = userMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(userList))
        {
            return userList.get(0);
        }
        return null;
    }

    @Override
    public User getById(int id)
    {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void register(String username, String password, String phone, String checkCode)
    {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setPhone(phone);
        userMapper.insert(user);
    }

    @Override
    public String generateCheckCode(String telephone)
    {
        return null;
    }

    @Override
    public void updatePassword(String telephone, String password, String checkCode)
    {

    }

    @Override
    public User getCurrentUser()
    {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        BoyingUserDetails userDetails = (BoyingUserDetails) auth.getPrincipal();
        return userDetails.getUser();
    }

    @Override
    public UserDetails loadUserByUsername(String username)
    {
        User user = getByUsername(username);
        if (user != null)
        {
            return new BoyingUserDetails(user);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public String login(String username, String password)
    {
        String token = null;
        //密码需要客户端加密后传递
        System.out.println("前端密码:" + password);
        System.out.println("修改:" + passwordEncoder.encode(password));
        try
        {
            UserDetails userDetails = loadUserByUsername(username);
            System.out.println("数据库的:" + userDetails.getPassword());
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
        }
        return token;
    }

    @Override
    public String refreshToken(String token)
    {
        return jwtTokenUtil.refreshHeadToken(token);
    }
}
