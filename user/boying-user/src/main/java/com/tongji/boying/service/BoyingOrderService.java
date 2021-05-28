package com.tongji.boying.service;

import com.tongji.boying.dto.orderParam.GetOrdersParam;
import com.tongji.boying.dto.orderParam.UserOrderParam;
import com.tongji.boying.model.BoyingOrder;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoyingOrderService {
    /**
     * 通过前端url上传过来秒杀活动id，然后下单接口内校验对应id是否属于对应商品且活动已开始
     */
    @Transactional
    void add(UserOrderParam param);

    void delete(int id);

    @Transactional
    void cancel(int id);

    List<BoyingOrder> list(GetOrdersParam param);

    BoyingOrder getItem(int id);

    /**
     * 检查该用户对该演出是否下单过,用于防止重复购买
     */
    boolean checkOrder(Integer showId);
}
