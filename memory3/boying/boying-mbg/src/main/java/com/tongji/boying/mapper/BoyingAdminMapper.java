package com.tongji.boying.mapper;

import com.tongji.boying.model.BoyingAdmin;

public interface BoyingAdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BoyingAdmin record);

    int insertSelective(BoyingAdmin record);

    BoyingAdmin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BoyingAdmin record);

    int updateByPrimaryKey(BoyingAdmin record);
}