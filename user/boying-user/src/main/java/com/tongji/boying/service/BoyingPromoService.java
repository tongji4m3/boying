package com.tongji.boying.service;

import com.tongji.boying.model.BoyingPromoModel;

public interface BoyingPromoService {
    BoyingPromoModel getPromo(Integer seatId); // 获取即将进行的、正在进行的秒杀活动
}
