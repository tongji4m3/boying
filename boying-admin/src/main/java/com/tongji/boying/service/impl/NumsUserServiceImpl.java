package com.tongji.boying.service.impl;

import com.tongji.boying.mapper.UserMapper;
import com.tongji.boying.mapper.UserOrderMapper;
import com.tongji.boying.model.User;
import com.tongji.boying.model.UserExample;
import com.tongji.boying.model.UserOrder;
import com.tongji.boying.model.UserOrderExample;
import com.tongji.boying.service.NumsUserService;
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
    public int ChangeUserStatusById(Integer id) {
        User user=userMapper.selectByPrimaryKey(id);
        if(!user.getStatus()) {
            user.setStatus(true);
        }
        else{
            user.setStatus(false);
        }
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public List<UserOrder> listOrders() {
        return userOrderMapper.selectByExample(new UserOrderExample());
    }
}
