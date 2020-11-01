package com.tongji.boying.service.impl;

import com.github.pagehelper.PageHelper;
import com.tongji.boying.mapper.TicketMapper;
import com.tongji.boying.model.*;
import com.tongji.boying.service.UserTicketService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserTicketServiceImpl implements UserTicketService
{
    private TicketMapper ticketMapper;

    @Override
    public int add(int orderId, int showClassId)
    {
        Ticket ticket = new Ticket();
        ticket.setOrderId(orderId);
        ticket.setShowClassId(showClassId);
        ticket.setQrCodeUrl("二维码地址,后续加上");

        return ticketMapper.insert(ticket);
    }

    @Override
    public List<Ticket> list(int orderId, Integer pageSize, Integer pageNum)
    {
        PageHelper.startPage(pageNum, pageSize);//分页相关
        TicketExample ticketExample = new TicketExample();
        ticketExample.createCriteria().andOrderIdEqualTo(orderId);
        return ticketMapper.selectByExample(ticketExample);
    }

    @Override
    public Ticket getItem(int id)
    {
        TicketExample ticketExample = new TicketExample();
        ticketExample.createCriteria().andOrderIdEqualTo(id);
        List<Ticket> tickets = ticketMapper.selectByExample(ticketExample);

        if (!CollectionUtils.isEmpty(tickets))
        {
            return tickets.get(0);
        }
        return null;
    }
}
