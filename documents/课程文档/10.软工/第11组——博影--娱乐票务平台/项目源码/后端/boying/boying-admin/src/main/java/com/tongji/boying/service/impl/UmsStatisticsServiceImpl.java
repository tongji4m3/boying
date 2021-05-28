package com.tongji.boying.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.tongji.boying.mapper.BoyingUserMapper;
import com.tongji.boying.mapper.BoyingOrderMapper;
import com.tongji.boying.model.BoyingUserExample;
import com.tongji.boying.model.BoyingOrder;
import com.tongji.boying.model.BoyingOrderExample;
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

    /**具体到秒的时间处理为当天
     * 处理时间为当天0点
     * @param date
     * @return
     */
    public Date dateDispose(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return java.sql.Date.valueOf(localDate);
    }

    /**时间往后加1天
     *
     * @param date
     * @return
     */
    public Date dateAddOneDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    @Override
    public long countOrderByDay(Date date) {
        /**已退订单也计算
         *
         */
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
        double sum = 0;
        Date dateStart = dateDispose(date);
        Date dateEnd = dateAddOneDay(dateStart);
        BoyingOrderExample example = new BoyingOrderExample();
        example.createCriteria().andTimeBetween(dateStart, dateEnd);
        List<BoyingOrder> list = userOrderMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(list)) {
            for (BoyingOrder order : list) {
                /**已取消订单不计算在内
                 *
                 */
                if (order.getStatus() != 3) {
                    sum += order.getMoney();
                }
            }
        }
        return sum;
    }


    @Override
    public double sumAllOrderMoney() {
        double sum = 0;
        BoyingOrderExample example = new BoyingOrderExample();
        example.createCriteria().andIdIsNotNull();
        List<BoyingOrder> list = userOrderMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(list)) {
            for (BoyingOrder order : list) {
                /**已取消订单不计算在内
                 *
                 */
                if (order.getStatus() != 3) {
                    sum += order.getMoney();
                }
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
