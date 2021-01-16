package com.tongji.boying.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public interface UmsStatisticsService {
    /**
     * 每日订单统计
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    long countOrderByDay(Date date);

    /**
     * 一段时间内订单统计
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    long countOrderForPeriod(Date dateStart, Date dateEnd);

    /**
     * 每日销售总额
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    double sumOrderMoneyByDay(Date date);


    /**
     * 一段时间内订单统计
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    double sumAllOrderMoney();

    /**
     * 每日新增用户统计
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    long countUserDailyGrowth(Date date);

    /**
     * 一段时间新增用户统计
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    long countUserGrowthForPeriod(Date dateStart, Date dateEnd);
}
