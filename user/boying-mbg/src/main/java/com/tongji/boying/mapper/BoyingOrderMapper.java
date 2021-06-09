package com.tongji.boying.mapper;

import com.tongji.boying.model.BoyingOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BoyingOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BoyingOrder record);

    int insertSelective(BoyingOrder record);

    BoyingOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BoyingOrder record);

    int updateByPrimaryKey(BoyingOrder record);

    List<BoyingOrder> selectByCondition(Map<String, Object> map);

    Integer selectByShowIdUserId(@Param("userId") Integer userId, @Param("showId")  Integer showId);
}