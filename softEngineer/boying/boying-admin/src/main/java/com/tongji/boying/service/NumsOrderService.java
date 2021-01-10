package com.tongji.boying.service;

import com.tongji.boying.model.BoyingOrder;

import java.util.List;

public interface NumsOrderService {
    /**
     * 列出所有用户订单
     */
    List<BoyingOrder> listOrders();

    /**
     * 删除某id订单
     */
    void deleteOrder(Integer id);

    void recoverOrder(Integer id);
}
