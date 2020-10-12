package com.tongji.service.impl;

import com.tongji.mbg.mapper.UserMapper;
import com.tongji.mbg.model.User;
import com.tongji.service.RedisService;
import com.tongji.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService
{
    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired
    private RedisService redisService;

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
        if(redisService.get("user")==null)
        {
            System.out.println("Redis First time ----");
            redisService.set("user",user.toString());
        }
        else
        {
            System.out.println("Redis next time ----");
            System.out.println(redisService.get("user"));
        }
        return user;
    }
}
