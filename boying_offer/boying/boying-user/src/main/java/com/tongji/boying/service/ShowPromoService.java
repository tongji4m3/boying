package com.tongji.boying.service;

import com.tongji.boying.model.PromoModel;

public interface ShowPromoService {
    //获取即将进行的、正在进行的秒杀活动
    PromoModel getPromo(Integer seatId);
}
