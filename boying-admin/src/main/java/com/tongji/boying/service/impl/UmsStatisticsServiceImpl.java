package com.tongji.boying.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.tongji.boying.mapper.AdminMapper;
import com.tongji.boying.mapper.UserOrderMapper;
import com.tongji.boying.model.AdminExample;
import com.tongji.boying.model.UserOrder;
import com.tongji.boying.model.UserOrderExample;
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
    private UserOrderMapper userOrderMapper;
    @Autowired
    private AdminMapper adminMapper;

    //具体到秒的时间处理为当天
    public Date dateDispose(Date date)
    {
        //处理时间为当天0点
        LocalDate localDate=date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        // java.time.LocalDate -> java.sql.Date
        return java.sql.Date.valueOf(localDate);
    }

    //时间往后加1天
    public Date dateAddOneDay(Date date)
    {
        //时间+1天
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    @Override
    public long countOrderByDay(Date date)
    {
        long orderCount=-1;
        Date dateStart=dateDispose(date);
        Date dateEnd=dateAddOneDay(dateStart);
        UserOrderExample example=new UserOrderExample();
        example.createCriteria().andTimeBetween(dateStart,dateEnd);
        orderCount= userOrderMapper.countByExample(example);
        return orderCount;
    }

    @Override
    public long countOrderForPeriod(Date dateStart,Date dateEnd)
    {
        long orderCount=-1;
        UserOrderExample example=new UserOrderExample();
        example.createCriteria().andTimeBetween(dateStart,dateEnd);
        orderCount = userOrderMapper.countByExample(example);
        return orderCount;
    }

    @Override
    public double sumOrderMoneyByDay(Date date)
    {
        double sum = 0;
        Date dateStart=dateDispose(date);
        Date dateEnd=dateAddOneDay(dateStart);
        UserOrderExample example=new UserOrderExample();
        example.createCriteria().andTimeBetween(dateStart,dateEnd);
        List<UserOrder> list=userOrderMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(list)) {
            for (UserOrder order : list) {
                sum += order.getMoney();
            }
        }
        return sum;
    }

    @Override
    public double sumOrderMoneyForPeriod(Date dateStart ,Date dateEnd) {
        double sum = 0;
        UserOrderExample example=new UserOrderExample();
        example.createCriteria().andTimeBetween(dateStart,dateEnd);
        List<UserOrder> list=userOrderMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(list)) {
            for (UserOrder order : list) {
                sum += order.getMoney();
            }
        }
        return sum;
    }

    @Override
    public long countAdminDailyGrowth(Date date) {
        long growth=-1;
        Date dateStart=dateDispose(date);
        Date dateEnd=dateAddOneDay(dateStart);
        AdminExample example=new AdminExample();
        example.createCriteria().andCreateTimeBetween(dateStart,dateEnd);
        growth = adminMapper.countByExample(example);
        return growth;
    }

    @Override
    public long countAdminGrowthForPeriod(Date dateStart, Date dateEnd) {
        long growth=-1;
        AdminExample example=new AdminExample();
        example.createCriteria().andCreateTimeBetween(dateStart,dateEnd);
        growth = adminMapper.countByExample(example);
        return growth;
    }
}
