package com.tongji.boying.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.orderParam.OrderParam;
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
    public List<BoyingOrder> listOrders(OrderParam param) {

        BoyingOrderExample boyingOrderExample = new BoyingOrderExample();
        BoyingOrderExample.Criteria criteria = boyingOrderExample.createCriteria();

        //根据演出Id搜索订单
        if (param.getShowId() != null && param.getShowId() != 0) {
            criteria.andShowIdEqualTo(param.getShowId());
        }
        //根据用户Id搜索订单
        if (param.getUserId() != null && param.getUserId() != 0) {
            criteria.andUserIdEqualTo(param.getUserId());
        }

        Integer pageNum = param.getPageNum();
        if (pageNum == null || pageNum == 0) pageNum = 0;
        Integer pageSize = param.getPageSize();
        if (pageSize == null || pageSize == 0) pageSize = 5;

        PageHelper.startPage(pageNum, pageSize);
        List<BoyingOrder> boyingOrders = userOrderMapper.selectByExample(boyingOrderExample);
        if (ObjectUtil.isEmpty(boyingOrders)) Asserts.fail("不存在任何订单");
        return boyingOrders;
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
