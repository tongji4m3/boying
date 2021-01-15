package com.tongji.boying.mapper;

import com.tongji.boying.model.BoyingCategory;

import java.util.List;

public interface BoyingCategoryMapper {
    List<BoyingCategory> selectList();

    int deleteByPrimaryKey(Integer id);

    int insert(BoyingCategory record);

    int insertSelective(BoyingCategory record);

    BoyingCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BoyingCategory record);

    int updateByPrimaryKey(BoyingCategory record);
}
