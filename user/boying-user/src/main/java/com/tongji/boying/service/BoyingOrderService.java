package com.tongji.boying.service;

import com.tongji.boying.dto.orderParam.GetOrdersParam;
import com.tongji.boying.dto.orderParam.UserOrderParam;
import com.tongji.boying.model.BoyingOrder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoyingOrderService {
    /**
     * 添加订单
     * 通过前端url上传过来秒杀活动id，然后下单接口内校验对应id是否属于对应商品且活动已开始
     */
    @Transactional
    void add(UserOrderParam param);

    /**
     * 删除订单
     *
     * @param id 订单表的id
     */
    @Transactional
    void delete(int id);


    @Transactional
    void cancel(int id);

    void finish(int id);

    List<BoyingOrder> list(GetOrdersParam param);

    BoyingOrder getItem(int id);
}
