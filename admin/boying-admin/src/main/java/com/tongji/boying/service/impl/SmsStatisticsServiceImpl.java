package com.tongji.boying.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.tongji.boying.mapper.BoyingOrderMapper;
import com.tongji.boying.mapper.BoyingUserMapper;
import com.tongji.boying.model.BoyingOrder;
import com.tongji.boying.model.BoyingOrderExample;
import com.tongji.boying.model.BoyingUserExample;
import com.tongji.boying.service.SmsStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SmsStatisticsServiceImpl implements SmsStatisticsService {
    @Autowired
    private BoyingOrderMapper boyingOrderMapper;
    @Autowired
    private BoyingUserMapper boyingUserMapper;

    //具体到秒的时间处理为当天
    public Date dateDispose(Date date) {
        //处理时间为当天0点
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        // java.time.LocalDate -> java.sql.Date
        return java.sql.Date.valueOf(localDate);
    }

    //时间往后加1天
    public Date dateAddOneDay(Date date) {
        //时间+1天
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    @Override
    public long countOrderByDay(Date date) {
        long orderCount = -1;
        Date dateStart = dateDispose(date);
        Date dateEnd = dateAddOneDay(dateStart);
        BoyingOrderExample example = new BoyingOrderExample();
        example.createCriteria().andTimeBetween(dateStart, dateEnd);
        orderCount = boyingOrderMapper.countByExample(example);
        return orderCount;
    }

    @Override
    public long countOrderForPeriod(Date dateStart, Date dateEnd) {
        long orderCount = -1;
        BoyingOrderExample example = new BoyingOrderExample();
        example.createCriteria().andTimeBetween(dateStart, dateEnd);
        orderCount = boyingOrderMapper.countByExample(example);
        return orderCount;
    }

    @Override
    public double sumOrderMoneyByDay(Date date) {
        double sum = 0;
        Date dateStart = dateDispose(date);
        Date dateEnd = dateAddOneDay(dateStart);
        BoyingOrderExample example = new BoyingOrderExample();
        example.createCriteria().andTimeBetween(dateStart, dateEnd);
        List<BoyingOrder> list = boyingOrderMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(list)) {
            for (BoyingOrder order : list) {
                sum += order.getOrderPrice();
            }
        }
        return sum;
    }

    @Override
    public double sumOrderMoneyForPeriod(Date dateStart, Date dateEnd) {
        double sum = 0;
        BoyingOrderExample example = new BoyingOrderExample();
        example.createCriteria().andTimeBetween(dateStart, dateEnd);
        List<BoyingOrder> list = boyingOrderMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(list)) {
            for (BoyingOrder order : list) {
                sum += order.getOrderPrice();
            }
        }
        return sum;
    }

    @Override
    public double sumAllOrderMoney() {
        double sum = 0;
        BoyingOrderExample example = new BoyingOrderExample();
        example.createCriteria().andIdIsNotNull();
        List<BoyingOrder> list = boyingOrderMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(list)) {
            for (BoyingOrder order : list) {
                sum += order.getOrderPrice();
            }
        }
        return sum;
    }

    @Override
    public long countUserDailyGrowth(Date date) {
        long growth = -1;
        Date dateStart = dateDispose(date);
        Date dateEnd = dateAddOneDay(dateStart);
        BoyingUserExample example = new BoyingUserExample();
        example.createCriteria().andCreateTimeBetween(dateStart, dateEnd);
        growth = boyingUserMapper.countByExample(example);
        return growth;
    }

    @Override
    public long countUserGrowthForPeriod(Date dateStart, Date dateEnd) {
        long growth = -1;
        BoyingUserExample example = new BoyingUserExample();
        example.createCriteria().andCreateTimeBetween(dateStart, dateEnd);
        growth = boyingUserMapper.countByExample(example);
        return growth;
    }
}
