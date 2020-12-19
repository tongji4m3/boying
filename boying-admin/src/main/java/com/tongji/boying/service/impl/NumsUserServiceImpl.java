package com.tongji.boying.service.impl;

import com.tongji.boying.dto.NumsUserParam;
import com.tongji.boying.mapper.UserMapper;
import com.tongji.boying.mapper.UserOrderMapper;
import com.tongji.boying.model.*;
import com.tongji.boying.service.NumsUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NumsUserServiceImpl implements NumsUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserOrderMapper userOrderMapper;

    @Override
    public int delete(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> listAllUsers() {
        return userMapper.selectByExample(new UserExample());
    }

    @Override
    public int ChangeUserStatusById(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (!user.getUserstatus()) {
            user.setUserstatus(true);
        } else {
            user.setUserstatus(false);
        }
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public List<UserOrder> listOrders() {
        return userOrderMapper.selectByExample(new UserOrderExample());
    }

    @Override
    public int deleteOrder(Integer id) {
        return userOrderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Integer id, NumsUserParam param) {
        User user = new User();
        BeanUtils.copyProperties(param, user);
        user.setUserId(id);
        int count = userMapper.updateByPrimaryKeySelective(user);
        return count;
    }

    @Override
    public int add(NumsUserParam param) {
        User user = new User();
        BeanUtils.copyProperties(param, user);
        int count = userMapper.insertSelective(user);
        return count;
    }
}
