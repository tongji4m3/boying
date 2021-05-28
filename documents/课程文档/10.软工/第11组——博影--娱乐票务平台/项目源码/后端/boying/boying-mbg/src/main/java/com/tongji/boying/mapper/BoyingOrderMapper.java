package com.tongji.boying.mapper;

import com.tongji.boying.model.BoyingOrder;
import com.tongji.boying.model.BoyingOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BoyingOrderMapper {
    long countByExample(BoyingOrderExample example);

    int deleteByExample(BoyingOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BoyingOrder record);

    int insertSelective(BoyingOrder record);

    List<BoyingOrder> selectByExample(BoyingOrderExample example);

    BoyingOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BoyingOrder record, @Param("example") BoyingOrderExample example);

    int updateByExample(@Param("record") BoyingOrder record, @Param("example") BoyingOrderExample example);

    int updateByPrimaryKeySelective(BoyingOrder record);

    int updateByPrimaryKey(BoyingOrder record);
}