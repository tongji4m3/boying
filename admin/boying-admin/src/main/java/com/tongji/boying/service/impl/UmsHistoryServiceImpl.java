package com.tongji.boying.service.impl;

import com.tongji.boying.mapper.BoyingHistoryMapper;
import com.tongji.boying.model.AdminCategoryExample;
import com.tongji.boying.model.BoyingHistory;
import com.tongji.boying.model.BoyingHistoryExample;
import com.tongji.boying.service.UmsHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 后台订单历史Service实现类
 */
@Service
public class UmsHistoryServiceImpl implements UmsHistoryService
{

    @Autowired
    private BoyingHistoryMapper boyingHistoryMapper;

    @Override
    public List<BoyingHistory> listAll()
    {
        BoyingHistoryExample example = new BoyingHistoryExample();
        return boyingHistoryMapper.selectByExample(example);
    }
}
