package com.tongji.boying.mapper;

import com.tongji.boying.model.BoyingCategory;

public interface BoyingCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BoyingCategory record);

    int insertSelective(BoyingCategory record);

    BoyingCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BoyingCategory record);

    int updateByPrimaryKey(BoyingCategory record);
}