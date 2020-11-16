package com.tongji.boying.service.impl;

import com.tongji.boying.mapper.UserMapper;
import com.tongji.boying.service.NumsUserService;
import org.springframework.beans.factory.annotation.Autowired;

public class NumsUserServiceImpl implements NumsUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int delete(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }
}
