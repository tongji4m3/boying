package com.tongji.boying.mapper;

import com.tongji.boying.model.BoyingTicket;

public interface BoyingTicketMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BoyingTicket record);

    int insertSelective(BoyingTicket record);

    BoyingTicket selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BoyingTicket record);

    int updateByPrimaryKey(BoyingTicket record);
}