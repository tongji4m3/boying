package com.tongji.boying.mapper;

import com.tongji.boying.model.BoyingAdmin;

public interface BoyingAdminMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BoyingAdmin record);

    int insertSelective(BoyingAdmin record);

    BoyingAdmin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BoyingAdmin record);

    int updateByPrimaryKey(BoyingAdmin record);
}