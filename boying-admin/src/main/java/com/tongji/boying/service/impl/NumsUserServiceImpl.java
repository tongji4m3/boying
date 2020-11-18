package com.tongji.boying.service.impl;

import com.tongji.boying.mapper.UserMapper;
import com.tongji.boying.model.User;
import com.tongji.boying.service.NumsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NumsUserServiceImpl implements NumsUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int delete(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
