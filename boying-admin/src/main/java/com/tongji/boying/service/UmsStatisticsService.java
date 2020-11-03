package com.tongji.boying.service;

import java.util.Date;

public interface UmsStatisticsService {
    /**
     * 每日订单统计
     */
    long countOrderByDay(Date date);

    /**
     * 一段时间内订单统计
     */
    long countOrderForPeriod(Date dateStart,Date dateEnd);

    /**
     * 每日销售总额
     */
    double sumOrderMoneyByDay(Date date);

    /**
     * 一段时间内销售总额
     */
    double sumOrderMoneyForPeriod(Date dateStart,Date dateEnd);

}
