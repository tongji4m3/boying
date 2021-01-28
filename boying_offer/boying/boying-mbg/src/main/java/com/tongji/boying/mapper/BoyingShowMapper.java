package com.tongji.boying.mapper;

import com.tongji.boying.model.BoyingShow;

import java.util.List;
import java.util.Map;

public interface BoyingShowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BoyingShow record);

    int insertSelective(BoyingShow record);

    BoyingShow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BoyingShow record);

    int updateByPrimaryKey(BoyingShow record);

    List<BoyingShow> selectList(Map<String, Object> map);
}