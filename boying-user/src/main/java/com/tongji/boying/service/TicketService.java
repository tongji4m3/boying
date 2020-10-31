package com.tongji.boying.service;

import com.tongji.boying.model.Frequent;
import com.tongji.boying.model.Ticket;

import java.util.List;

public interface TicketService
{
    int add(int orderId, int showClassId);

    List<Ticket> list(int orderId, Integer pageSize, Integer pageNum);

    Ticket getItem(int id);
}
