package com.tongji.boying.mapper;

import com.tongji.boying.model.BoyingTicket;

import java.util.List;

public interface BoyingTicketMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BoyingTicket record);

    int insertSelective(BoyingTicket record);

    BoyingTicket selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BoyingTicket record);

    int updateByPrimaryKey(BoyingTicket record);

    List<BoyingTicket> getListByOrderId(int orderId);

    void deleteTicketsList(Integer orderId);
}
