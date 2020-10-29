package com.tongji.boying.mapper;

import com.tongji.boying.model.Session;
import com.tongji.boying.model.SessionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SessionMapper {
    long countByExample(SessionExample example);

    int deleteByExample(SessionExample example);

    int deleteByPrimaryKey(Integer sessionId);

    int insert(Session record);

    int insertSelective(Session record);

    List<Session> selectByExample(SessionExample example);

    Session selectByPrimaryKey(Integer sessionId);

    int updateByExampleSelective(@Param("record") Session record, @Param("example") SessionExample example);

    int updateByExample(@Param("record") Session record, @Param("example") SessionExample example);

    int updateByPrimaryKeySelective(Session record);

    int updateByPrimaryKey(Session record);
}