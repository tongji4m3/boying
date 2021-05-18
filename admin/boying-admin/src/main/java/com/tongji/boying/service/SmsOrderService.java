package com.tongji.boying.service;

import com.tongji.boying.dto.SmsGetOrdersParam;
import com.tongji.boying.model.BoyingOrder;

import java.util.List;

public interface SmsOrderService {
    List<BoyingOrder> list(SmsGetOrdersParam param);

    BoyingOrder getItem(int id);

    void deleteItem(int id);

    void recoverItem(int id);
}
