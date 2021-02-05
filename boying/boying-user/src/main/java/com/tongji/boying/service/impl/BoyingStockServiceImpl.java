package com.tongji.boying.service.impl;

import com.tongji.boying.mapper.BoyingCategoryMapper;
import com.tongji.boying.mapper.BoyingStockMapper;
import com.tongji.boying.model.BoyingStock;
import com.tongji.boying.service.BoyingStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoyingStockServiceImpl implements BoyingStockService {
    @Autowired
    private BoyingStockMapper boyingStockMapper;

    @Override
    public BoyingStock selectByPrimaryKey(Integer id) {
        return boyingStockMapper.selectByPrimaryKey(id);
    }
}
