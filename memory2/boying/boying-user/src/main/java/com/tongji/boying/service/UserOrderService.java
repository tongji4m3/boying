package com.tongji.boying.service;

import com.tongji.boying.dto.orderParam.GetOrdersParam;
import com.tongji.boying.dto.orderParam.UserOrderParam;
import com.tongji.boying.model.BoyingOrder;

import java.util.List;

public interface UserOrderService {
    /**
     * 添加订单
     */
    void add(UserOrderParam param);

    /**
     * 删除订单
     *
     * @param id 订单表的id
     */
    void delete(int id);


    void cancel(int id);

    void finish(int id);

    List<BoyingOrder> list(GetOrdersParam param);

    BoyingOrder getItem(int id);

    void generate(Integer count);
}
