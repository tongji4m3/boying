package com.tongji.boying.service;

import com.tongji.boying.model.BoyingPromo;

public interface ShowPromoService {
    //获取即将进行的、正在进行的秒杀活动
    BoyingPromo getPromo(Integer seatId);
}
