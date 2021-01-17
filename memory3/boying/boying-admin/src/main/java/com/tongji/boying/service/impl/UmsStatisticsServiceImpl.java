package com.tongji.boying.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.tongji.boying.mapper.BoyingOrderMapper;
import com.tongji.boying.mapper.BoyingUserMapper;
import com.tongji.boying.model.BoyingOrder;
import com.tongji.boying.model.BoyingOrderExample;
import com.tongji.boying.model.BoyingUserExample;
import com.tongji.boying.service.UmsStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class UmsStatisticsServiceImpl implements UmsStatisticsService {
    @Autowired
    private BoyingOrderMapper userOrderMapper;
    @Autowired
    private BoyingUserMapper userMapper;

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
        //已退订单也计算
        long orderCount = -1;
        Date dateStart = dateDispose(date);
        Date dateEnd = dateAddOneDay(dateStart);
        BoyingOrderExample example = new BoyingOrderExample();
        example.createCriteria().andTimeBetween(dateStart, dateEnd);
        orderCount = userOrderMapper.countByExample(example);
        return orderCount;
    }

    @Override
    public long countOrderForPeriod(Date dateStart, Date dateEnd) {
        long orderCount = -1;
        BoyingOrderExample example = new BoyingOrderExample();
        example.createCriteria().andTimeBetween(dateStart, dateEnd);
        orderCount = userOrderMapper.countByExample(example);
        return orderCount;
    }

    @Override
    public double sumOrderMoneyByDay(Date date) {

        Date dateStart = dateDispose(date);
        Date dateEnd = dateAddOneDay(dateStart);
        BoyingOrderExample example = new BoyingOrderExample();
        BoyingOrderExample.Criteria criteria = example.createCriteria();
        criteria.andTimeBetween(dateStart, dateEnd);
        criteria.andStatusNotEqualTo(3);

        /* List<Double> doubles = userOrderMapper.selectMoney(example);
        for (Double money : doubles) {
            sum += money;
        }*/

        /*List<BoyingOrder> list = userOrderMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(list)) {
            for (BoyingOrder order : list) {
                //已取消订单不计算在内
                if (order.getStatus() != 3) {
                    sum += order.getMoney();
                }
            }
        }*/
        Double total = userOrderMapper.selectMoney(example);

        if (total == null) return 0;
        return total;
    }


    @Override
    public double sumAllOrderMoney() {
        BoyingOrderExample example = new BoyingOrderExample();
        //已取消订单不计算在内
        example.createCriteria().andIdIsNotNull().andStatusNotEqualTo(3);

        Double total = userOrderMapper.selectMoney(example);

        if (total == null) return 0;
        return total;

//        if (CollUtil.isNotEmpty(list)) {
//            for (BoyingOrder order : list) {
//                //已取消订单不计算在内
//                if (order.getStatus() != 3) {
//                    sum += order.getMoney();
//                }
//            }
//        }

//        List<Double> doubles = userOrderMapper.selectMoney(example);
//        for (Double money : doubles) {
//            sum += money;
//        }
    }

    @Override
    public long countUserDailyGrowth(Date date) {
        long growth = -1;
        Date dateStart = dateDispose(date);
        Date dateEnd = dateAddOneDay(dateStart);
        BoyingUserExample example = new BoyingUserExample();
        example.createCriteria().andCreateTimeBetween(dateStart, dateEnd);
        growth = userMapper.countByExample(example);
        return growth;
    }

    @Override
    public long countUserGrowthForPeriod(Date dateStart, Date dateEnd) {
        long growth = -1;
        BoyingUserExample example = new BoyingUserExample();
        example.createCriteria().andCreateTimeBetween(dateStart, dateEnd);
        growth = userMapper.countByExample(example);
        return growth;
    }
}
