package com.tongji.boying.mapper;

import com.tongji.boying.model.BoyingCategory;
import com.tongji.boying.model.BoyingCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BoyingCategoryMapper {
    long countByExample(BoyingCategoryExample example);

    int deleteByExample(BoyingCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BoyingCategory record);

    int insertSelective(BoyingCategory record);

    List<BoyingCategory> selectByExample(BoyingCategoryExample example);

    BoyingCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BoyingCategory record, @Param("example") BoyingCategoryExample example);

    int updateByExample(@Param("record") BoyingCategory record, @Param("example") BoyingCategoryExample example);

    int updateByPrimaryKeySelective(BoyingCategory record);

    int updateByPrimaryKey(BoyingCategory record);
}