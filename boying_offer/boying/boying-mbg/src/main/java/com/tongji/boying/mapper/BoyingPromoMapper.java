package com.tongji.boying.mapper;

import com.tongji.boying.model.BoyingPromo;

public interface BoyingPromoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BoyingPromo record);

    int insertSelective(BoyingPromo record);

    BoyingPromo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BoyingPromo record);

    int updateByPrimaryKey(BoyingPromo record);

    BoyingPromo selectBySeatId(Integer seatId);
}