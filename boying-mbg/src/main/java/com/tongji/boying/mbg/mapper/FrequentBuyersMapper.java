package com.tongji.boying.mbg.mapper;

import com.tongji.boying.mbg.model.FrequentBuyers;
import com.tongji.boying.mbg.model.FrequentBuyersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FrequentBuyersMapper {
    long countByExample(FrequentBuyersExample example);

    int deleteByExample(FrequentBuyersExample example);

    int deleteByPrimaryKey(Integer frequentId);

    int insert(FrequentBuyers record);

    int insertSelective(FrequentBuyers record);

    List<FrequentBuyers> selectByExample(FrequentBuyersExample example);

    FrequentBuyers selectByPrimaryKey(Integer frequentId);

    int updateByExampleSelective(@Param("record") FrequentBuyers record, @Param("example") FrequentBuyersExample example);

    int updateByExample(@Param("record") FrequentBuyers record, @Param("example") FrequentBuyersExample example);

    int updateByPrimaryKeySelective(FrequentBuyers record);

    int updateByPrimaryKey(FrequentBuyers record);
}