package com.tongji.boying.mbg.mapper;

import com.tongji.boying.mbg.model.ShowSession;
import com.tongji.boying.mbg.model.ShowSessionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShowSessionMapper {
    long countByExample(ShowSessionExample example);

    int deleteByExample(ShowSessionExample example);

    int deleteByPrimaryKey(Integer showSessionId);

    int insert(ShowSession record);

    int insertSelective(ShowSession record);

    List<ShowSession> selectByExample(ShowSessionExample example);

    ShowSession selectByPrimaryKey(Integer showSessionId);

    int updateByExampleSelective(@Param("record") ShowSession record, @Param("example") ShowSessionExample example);

    int updateByExample(@Param("record") ShowSession record, @Param("example") ShowSessionExample example);

    int updateByPrimaryKeySelective(ShowSession record);

    int updateByPrimaryKey(ShowSession record);
}