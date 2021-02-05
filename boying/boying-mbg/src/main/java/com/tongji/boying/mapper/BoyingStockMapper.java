package com.tongji.boying.mapper;

import com.tongji.boying.model.BoyingStock;

public interface BoyingStockMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BoyingStock record);

    int insertSelective(BoyingStock record);

    BoyingStock selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BoyingStock record);

    int updateByPrimaryKey(BoyingStock record);
}