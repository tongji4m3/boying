package com.tongji.boying.mapper;

import com.tongji.boying.model.Frequent;
import com.tongji.boying.model.FrequentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FrequentMapper {
    long countByExample(FrequentExample example);

    int deleteByExample(FrequentExample example);

    int deleteByPrimaryKey(Integer frequentId);

    int insert(Frequent record);

    int insertSelective(Frequent record);

    List<Frequent> selectByExample(FrequentExample example);

    Frequent selectByPrimaryKey(Integer frequentId);

    int updateByExampleSelective(@Param("record") Frequent record, @Param("example") FrequentExample example);

    int updateByExample(@Param("record") Frequent record, @Param("example") FrequentExample example);

    int updateByPrimaryKeySelective(Frequent record);

    int updateByPrimaryKey(Frequent record);
}