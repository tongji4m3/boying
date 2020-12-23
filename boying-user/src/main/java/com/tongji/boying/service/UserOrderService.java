package com.tongji.boying.service;

import com.tongji.boying.dto.UserOrderParam;
import com.tongji.boying.model.UserOrder;

import java.util.List;

public interface UserOrderService {
    /**
     * 添加订单
     */
    int add(UserOrderParam param);

    /**
     * 删除订单
     *
     * @param id 订单表的id
     */
    int delete(int id);


    int cancel(int id);


    /**
     * 返回当前用户的订单
     *
     * @param pageNum
     * @param pageSize
     */
    List<UserOrder> list(Integer status, Integer pageNum, Integer pageSize);

    /**
     * 获取订单详情
     *
     * @param id 订单id
     */
    UserOrder getItem(int id);
}
