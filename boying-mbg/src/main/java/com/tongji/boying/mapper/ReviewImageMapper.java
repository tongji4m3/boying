package com.tongji.boying.mapper;

import com.tongji.boying.model.ReviewImage;
import com.tongji.boying.model.ReviewImageExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReviewImageMapper
{
    long countByExample(ReviewImageExample example);

    int deleteByExample(ReviewImageExample example);

    int deleteByPrimaryKey(Integer reviewImageId);

    int insert(ReviewImage record);

    int insertSelective(ReviewImage record);

    List<ReviewImage> selectByExample(ReviewImageExample example);

    ReviewImage selectByPrimaryKey(Integer reviewImageId);

    int updateByExampleSelective(@Param("record") ReviewImage record, @Param("example") ReviewImageExample example);

    int updateByExample(@Param("record") ReviewImage record, @Param("example") ReviewImageExample example);

    int updateByPrimaryKeySelective(ReviewImage record);

    int updateByPrimaryKey(ReviewImage record);
}
