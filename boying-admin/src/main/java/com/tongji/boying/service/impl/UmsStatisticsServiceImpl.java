package com.tongji.boying.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.tongji.boying.mapper.UserOrderMapper;
import com.tongji.boying.model.UserOrder;
import com.tongji.boying.model.UserOrderExample;
import com.tongji.boying.service.UmsStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UmsStatisticsServiceImpl implements UmsStatisticsService {
    @Autowired
    private UserOrderMapper userOrderMapper;

    @Override
    public long countOrderByDay(Date date)
    {
        UserOrderExample example=new UserOrderExample();
        example.createCriteria().andTimeEqualTo(date);
        return userOrderMapper.countByExample(example);
    }

    @Override
    public long countOrderForPeriod(Date dateStart,Date dateEnd)
    {
        UserOrderExample example=new UserOrderExample();
        example.createCriteria().andTimeBetween(dateStart,dateEnd);
        return userOrderMapper.countByExample(example);
    }

    @Override
    public double sumOrderMoneyByDay(Date date)
    {
        double sum = 0;
        UserOrderExample example=new UserOrderExample();
        example.createCriteria().andTimeEqualTo(date);
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
}
