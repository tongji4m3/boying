package com.tongji.boying.service.impl;

import com.tongji.boying.dto.NumsUserParam;
import com.tongji.boying.mapper.BoyingOrderMapper;
import com.tongji.boying.mapper.BoyingUserMapper;
import com.tongji.boying.mapper.BoyingUserMapper;
import com.tongji.boying.mapper.BoyingOrderMapper;
import com.tongji.boying.model.BoyingUser;
import com.tongji.boying.model.BoyingUserExample;
import com.tongji.boying.model.BoyingOrder;
import com.tongji.boying.model.BoyingOrderExample;
import com.tongji.boying.service.NumsUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NumsUserServiceImpl implements NumsUserService {

    @Autowired
    private BoyingUserMapper userMapper;
    @Autowired
    private BoyingOrderMapper userOrderMapper;

    @Override
    public int delete(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public BoyingUser getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<BoyingUser> listAllUsers() {
        return userMapper.selectByExample(new BoyingUserExample());
    }

    @Override
    public int ChangeUserStatusById(Integer id) {
        BoyingUser user = userMapper.selectByPrimaryKey(id);
        if (!user.getAdminDelete()) {
            user.setAdminDelete(true);
        }
        else {
            user.setAdminDelete(false);
        }
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public List<BoyingOrder> listOrders() {
        return userOrderMapper.selectByExample(new BoyingOrderExample());
    }

    @Override
    public int deleteOrder(Integer id) {
        return userOrderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Integer id, NumsUserParam param) {
        BoyingUser user = new BoyingUser();
        BeanUtils.copyProperties(param, user);
        user.setId(id);
        int count = userMapper.updateByPrimaryKeySelective(user);
        return count;
    }

    @Override
    public int add(NumsUserParam param) {
        BoyingUser user = new BoyingUser();
        BeanUtils.copyProperties(param, user);
        int count = userMapper.insertSelective(user);
        return count;
    }
}
