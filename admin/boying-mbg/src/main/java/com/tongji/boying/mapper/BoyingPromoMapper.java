package com.tongji.boying.mapper;

import com.tongji.boying.model.BoyingPromo;
import com.tongji.boying.model.BoyingPromoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BoyingPromoMapper {
    long countByExample(BoyingPromoExample example);

    int deleteByExample(BoyingPromoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BoyingPromo record);

    int insertSelective(BoyingPromo record);

    List<BoyingPromo> selectByExample(BoyingPromoExample example);

    BoyingPromo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BoyingPromo record, @Param("example") BoyingPromoExample example);

    int updateByExample(@Param("record") BoyingPromo record, @Param("example") BoyingPromoExample example);

    int updateByPrimaryKeySelective(BoyingPromo record);

    int updateByPrimaryKey(BoyingPromo record);
}