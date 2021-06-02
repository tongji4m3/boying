package com.tongji.boying.mapper;

import com.tongji.boying.model.BoyingHistory;
import com.tongji.boying.model.BoyingHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BoyingHistoryMapper {
    long countByExample(BoyingHistoryExample example);

    int deleteByExample(BoyingHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BoyingHistory record);

    int insertSelective(BoyingHistory record);

    List<BoyingHistory> selectByExample(BoyingHistoryExample example);

    BoyingHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BoyingHistory record, @Param("example") BoyingHistoryExample example);

    int updateByExample(@Param("record") BoyingHistory record, @Param("example") BoyingHistoryExample example);

    int updateByPrimaryKeySelective(BoyingHistory record);

    int updateByPrimaryKey(BoyingHistory record);
}