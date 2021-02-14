package com.tongji.boying.mapper;

import com.tongji.boying.model.ShowClass;
import com.tongji.boying.model.ShowClassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShowClassMapper {
    long countByExample(ShowClassExample example);

    int deleteByExample(ShowClassExample example);

    int deleteByPrimaryKey(Integer showClassId);

    int insert(ShowClass record);

    int insertSelective(ShowClass record);

    List<ShowClass> selectByExample(ShowClassExample example);

    ShowClass selectByPrimaryKey(Integer showClassId);

    int updateByExampleSelective(@Param("record") ShowClass record, @Param("example") ShowClassExample example);

    int updateByExample(@Param("record") ShowClass record, @Param("example") ShowClassExample example);

    int updateByPrimaryKeySelective(ShowClass record);

    int updateByPrimaryKey(ShowClass record);
}