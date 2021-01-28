package com.tongji.boying.mapper;

import com.tongji.boying.model.BoyingUser;

import java.util.Map;

public interface BoyingUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BoyingUser record);

    int insertSelective(BoyingUser record);

    BoyingUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BoyingUser record);

    int updateByPrimaryKey(BoyingUser record);

    BoyingUser selectByUsername(String username);

    BoyingUser selectByUsernameAndPhone(Map<String, Object> map);

    BoyingUser selectByPhone(String telephone);
}