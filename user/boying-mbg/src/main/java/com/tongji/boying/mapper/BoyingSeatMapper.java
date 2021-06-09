package com.tongji.boying.mapper;

import com.tongji.boying.model.BoyingSeat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BoyingSeatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BoyingSeat record);

    int insertSelective(BoyingSeat record);

    BoyingSeat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BoyingSeat record);

    int updateByPrimaryKey(BoyingSeat record);

    List<BoyingSeat> selectList(Integer showId);

    Integer decreaseStock(@Param("seatId") Integer seatId,@Param("ticketCount") Integer ticketCount);

    void increaseStock(@Param("seatId") Integer seatId, @Param("ticketCount") Integer ticketCount);
}
