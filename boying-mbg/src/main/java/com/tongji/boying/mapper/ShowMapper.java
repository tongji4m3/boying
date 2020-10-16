package com.tongji.boying.mapper;

import com.tongji.boying.model.Show;
import com.tongji.boying.model.ShowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShowMapper {
    long countByExample(ShowExample example);

    int deleteByExample(ShowExample example);

    int deleteByPrimaryKey(Integer showId);

    int insert(Show record);

    int insertSelective(Show record);

    List<Show> selectByExampleWithBLOBs(ShowExample example);

    List<Show> selectByExample(ShowExample example);

    Show selectByPrimaryKey(Integer showId);

    int updateByExampleSelective(@Param("record") Show record, @Param("example") ShowExample example);

    int updateByExampleWithBLOBs(@Param("record") Show record, @Param("example") ShowExample example);

    int updateByExample(@Param("record") Show record, @Param("example") ShowExample example);

    int updateByPrimaryKeySelective(Show record);

    int updateByPrimaryKeyWithBLOBs(Show record);

    int updateByPrimaryKey(Show record);
}
