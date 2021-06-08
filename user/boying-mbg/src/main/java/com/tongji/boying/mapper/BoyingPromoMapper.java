package com.tongji.boying.mapper;

import com.tongji.boying.model.BoyingPromo;

public interface BoyingPromoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BoyingPromo record);

    int insertSelective(BoyingPromo record);

    BoyingPromo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BoyingPromo record);

    int updateByPrimaryKey(BoyingPromo record);

    // 不查过期了的活动，而且要保证当前活动只有一个
    BoyingPromo selectBySeatId(Integer seatId);
}