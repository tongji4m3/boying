package com.tongji.boying.service;

import java.util.Date;

public interface UmsStatisticsService {
    /**
     * 每日订单统计
     */
    long countOrderByDay(Date date);


}
