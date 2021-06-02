package com.tongji.boying.mapper;

import com.tongji.boying.model.BoyingHistory;

public interface BoyingHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BoyingHistory record);

    int insertSelective(BoyingHistory record);

    BoyingHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BoyingHistory record);

    int updateByPrimaryKey(BoyingHistory record);
}