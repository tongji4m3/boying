package com.tongji.boying.mapper;

import com.tongji.boying.model.BoyingSeat;

import java.util.List;

public interface BoyingSeatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BoyingSeat record);

    int insertSelective(BoyingSeat record);

    BoyingSeat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BoyingSeat record);

    int updateByPrimaryKey(BoyingSeat record);

    List<BoyingSeat> selectList(Integer showId);

    void updateSeatsStock(Integer showId);
}
