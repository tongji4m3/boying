package com.tongji.boying.service.impl;

import com.tongji.boying.mapper.UserOrderMapper;
import com.tongji.boying.model.UserOrderExample;
import com.tongji.boying.service.UmsStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
}
