package com.tongji.boying.mapper;

import com.tongji.boying.model.BoyingShow;
import com.tongji.boying.model.BoyingShowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoyingShowMapper {
    long countByExample(BoyingShowExample example);

    int deleteByExample(BoyingShowExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BoyingShow record);

    int insertSelective(BoyingShow record);

    List<BoyingShow> selectByExample(BoyingShowExample example);

    BoyingShow selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BoyingShow record, @Param("example") BoyingShowExample example);

    int updateByExample(@Param("record") BoyingShow record, @Param("example") BoyingShowExample example);

    int updateByPrimaryKeySelective(BoyingShow record);

    int updateByPrimaryKey(BoyingShow record);
}