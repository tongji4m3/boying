package com.tongji.boying.mapper;

import com.tongji.boying.model.BoyingTicket;

public interface BoyingTicketMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BoyingTicket record);

    int insertSelective(BoyingTicket record);

    BoyingTicket selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BoyingTicket record);

    int updateByPrimaryKey(BoyingTicket record);
}