package com.tongji.boying.service.impl;

import com.github.pagehelper.PageHelper;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.mapper.BoyingTicketMapper;
import com.tongji.boying.mapper.BoyingOrderMapper;
import com.tongji.boying.model.BoyingTicket;
import com.tongji.boying.model.BoyingTicketExample;
import com.tongji.boying.service.UserService;
import com.tongji.boying.service.UserTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTicketServiceImpl implements UserTicketService {
    @Autowired
    private BoyingTicketMapper ticketMapper;
    @Autowired
    private BoyingOrderMapper orderMapper;
    @Autowired
    private UserService userService;

    @Override
    public int add(int orderId, int seatId) {
        BoyingTicket ticket = new BoyingTicket();
        ticket.setOrderId(orderId);
        ticket.setSeatId(seatId);
        ticket.setQrCodeUrl("https://tongji4m3.oss-cn-beijing.aliyuncs.com/1608898790.jpg");

        return ticketMapper.insertSelective(ticket);
    }

    @Override
    public List<BoyingTicket> list(int orderId) {
        BoyingTicketExample ticketExample = new BoyingTicketExample();
        ticketExample.createCriteria().andOrderIdEqualTo(orderId);
        List<BoyingTicket> boyingTickets = ticketMapper.selectByExample(ticketExample);
        if (boyingTickets == null || boyingTickets.size() == 0) {
            Asserts.fail("该订单没有包含票！");
        }
        return boyingTickets;
    }
}
