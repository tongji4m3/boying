package com.tongji.boying.mapper;

import com.tongji.boying.model.BoyingShow;

public interface BoyingShowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BoyingShow record);

    int insertSelective(BoyingShow record);

    BoyingShow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BoyingShow record);

    int updateByPrimaryKey(BoyingShow record);
}