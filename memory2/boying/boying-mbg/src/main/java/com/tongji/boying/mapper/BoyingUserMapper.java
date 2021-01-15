package com.tongji.boying.mapper;

import com.tongji.boying.model.BoyingUser;

public interface BoyingUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BoyingUser record);

    int insertSelective(BoyingUser record);

    BoyingUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BoyingUser record);

    int updateByPrimaryKey(BoyingUser record);
}