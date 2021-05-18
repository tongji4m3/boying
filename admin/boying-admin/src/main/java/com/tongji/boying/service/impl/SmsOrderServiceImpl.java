package com.tongji.boying.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.SmsGetOrdersParam;
import com.tongji.boying.mapper.BoyingOrderMapper;
import com.tongji.boying.model.*;
import com.tongji.boying.service.SmsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmsOrderServiceImpl implements SmsOrderService {
    @Autowired
    private BoyingOrderMapper boyingOrderMapper;

    @Override
    public List<BoyingOrder> list(SmsGetOrdersParam param) {
        Integer pageNum = param.getPageNum();
        Integer pageSize = param.getPageSize();
        if (pageNum == null || pageNum == 0) pageNum = 1;
        if (pageSize == null || pageSize == 0) pageSize = 5;

        PageHelper.startPage(pageNum, pageSize);
        BoyingOrderExample example = new BoyingOrderExample();
        BoyingOrderExample.Criteria criteria = example.createCriteria();


        if (param.getShowId() != null && param.getShowId() != 0)
        {
            criteria.andShowIdEqualTo(param.getShowId());
        }

        if (param.getUserId() != null && param.getUserId() != 0)
        {
            criteria.andShowIdEqualTo(param.getUserId());
        }

        return boyingOrderMapper.selectByExample(example);
    }

    @Override
    public BoyingOrder getItem(int id) {
        BoyingOrder order = boyingOrderMapper.selectByPrimaryKey(id);
        if (order.getAdminDelete()) {
            Asserts.fail("管理员已删除此订单！如有疑惑，请联系客服！");
        }
        return order;
    }

    @Override
    public void deleteItem(int id) {
        BoyingOrder order = boyingOrderMapper.selectByPrimaryKey(id);

        if (order.getAdminDelete()) {
            Asserts.fail("管理员已删除此订单！如有疑惑，请联系客服！");
        }

        order.setAdminDelete(true);
        boyingOrderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public void recoverItem(int id) {
        BoyingOrder order = boyingOrderMapper.selectByPrimaryKey(id);
        order.setAdminDelete(false);
        boyingOrderMapper.updateByPrimaryKeySelective(order);
    }
}
