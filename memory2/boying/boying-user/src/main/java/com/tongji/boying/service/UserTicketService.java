package com.tongji.boying.service;

import com.tongji.boying.dto.orderParam.TicketReturn;
import com.tongji.boying.model.BoyingTicket;

import java.util.List;

public interface UserTicketService {
    void add(int orderId, int seatId);

    List<TicketReturn> list(int orderId);
}
