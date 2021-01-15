package com.tongji.boying.mapper;

import com.tongji.boying.model.BoyingSeat;

public interface BoyingSeatMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BoyingSeat record);

    int insertSelective(BoyingSeat record);

    BoyingSeat selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BoyingSeat record);

    int updateByPrimaryKey(BoyingSeat record);
}