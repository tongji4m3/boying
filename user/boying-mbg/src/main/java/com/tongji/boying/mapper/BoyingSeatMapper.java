package com.tongji.boying.mapper;

import com.tongji.boying.model.BoyingSeat;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoyingSeatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BoyingSeat record);

    int insertSelective(BoyingSeat record);

    BoyingSeat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BoyingSeat record);

    int updateByPrimaryKey(BoyingSeat record);

    List<BoyingSeat> selectList(Integer showId);

    Integer decreaseStock(Integer seatId,Integer ticketCount);

    void increaseStock(Integer seatId, Integer ticketCount);
}
