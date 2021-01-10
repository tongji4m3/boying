package com.tongji.boying.service.impl;

import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.mapper.BoyingOrderMapper;
import com.tongji.boying.model.BoyingOrder;
import com.tongji.boying.model.BoyingOrderExample;
import com.tongji.boying.service.NumsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NumsOrderServiceImpl implements NumsOrderService {
    @Autowired
    private BoyingOrderMapper userOrderMapper;

    @Override
    public List<BoyingOrder> listOrders() {
        return userOrderMapper.selectByExample(new BoyingOrderExample());
    }

    @Override
    public void deleteOrder(Integer id) {
        BoyingOrder boyingOrder = userOrderMapper.selectByPrimaryKey(id);
        if(boyingOrder==null) Asserts.fail("要删除的订单不存在！");
        boyingOrder.setAdminDelete(true);
        userOrderMapper.updateByPrimaryKeySelective(boyingOrder);
    }

    @Override
    public void recoverOrder(Integer id) {
        BoyingOrder boyingOrder = userOrderMapper.selectByPrimaryKey(id);
        if(boyingOrder==null) Asserts.fail("要恢复的订单不存在！");
        boyingOrder.setAdminDelete(false);
        userOrderMapper.updateByPrimaryKeySelective(boyingOrder);
    }
}
