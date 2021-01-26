package com.tongji.boying.mapper;

import com.tongji.boying.model.BoyingPassword;

public interface BoyingPasswordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BoyingPassword record);

    int insertSelective(BoyingPassword record);

    BoyingPassword selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BoyingPassword record);

    int updateByPrimaryKey(BoyingPassword record);
}