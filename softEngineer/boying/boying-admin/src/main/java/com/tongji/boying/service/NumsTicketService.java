package com.tongji.boying.service;

import com.tongji.boying.dto.orderParam.TicketParam;
import com.tongji.boying.model.BoyingTicket;

import java.util.List;

public interface NumsTicketService {
    List<BoyingTicket> listTickets(TicketParam param);
}
