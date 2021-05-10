package com.tongji.boying.mapper;

import com.tongji.boying.model.BoyingStock;
import com.tongji.boying.model.BoyingStockExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoyingStockMapper {
    long countByExample(BoyingStockExample example);

    int deleteByExample(BoyingStockExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BoyingStock record);

    int insertSelective(BoyingStock record);

    List<BoyingStock> selectByExample(BoyingStockExample example);

    BoyingStock selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BoyingStock record, @Param("example") BoyingStockExample example);

    int updateByExample(@Param("record") BoyingStock record, @Param("example") BoyingStockExample example);

    int updateByPrimaryKeySelective(BoyingStock record);

    int updateByPrimaryKey(BoyingStock record);
}