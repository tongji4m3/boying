package com.tongji.boying.mapper;

import com.tongji.boying.model.BoyingShow;
import com.tongji.boying.model.BoyingShowExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BoyingShowMapper {
    long countByExample(BoyingShowExample example);

    int deleteByExample(BoyingShowExample example);

    int deleteByPrimaryKey(Integer showId);

    int insert(BoyingShow record);

    int insertSelective(BoyingShow record);

    List<BoyingShow> selectByExampleWithBLOBs(BoyingShowExample example);

    List<BoyingShow> selectByExample(BoyingShowExample example);

    BoyingShow selectByPrimaryKey(Integer showId);

    int updateByExampleSelective(@Param("record") BoyingShow record, @Param("example") BoyingShowExample example);

    int updateByExampleWithBLOBs(@Param("record") BoyingShow record, @Param("example") BoyingShowExample example);

    int updateByExample(@Param("record") BoyingShow record, @Param("example") BoyingShowExample example);

    int updateByPrimaryKeySelective(BoyingShow record);

    int updateByPrimaryKeyWithBLOBs(BoyingShow record);

    int updateByPrimaryKey(BoyingShow record);
}
