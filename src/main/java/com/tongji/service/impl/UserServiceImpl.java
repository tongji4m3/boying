package com.tongji.service.impl;

import com.tongji.mbg.mapper.UserMapper;
import com.tongji.mbg.model.User;
import com.tongji.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserMapper userMapper;

    @Override
    public User select(int id)
    {
        System.out.println(userMapper.selectByPrimaryKey(id));
        User user = new User();
        user.setId(2);
        user.setUsername("zhanx");
        user.setPassword("AXXXX");
        System.out.println(userMapper.insert(user));
        System.out.println(user);
        return user;
    }
}
