package com.tongji.boying.service.impl;

import com.github.pagehelper.PageHelper;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.mapper.TicketMapper;
import com.tongji.boying.mapper.UserOrderMapper;
import com.tongji.boying.model.Ticket;
import com.tongji.boying.model.TicketExample;
import com.tongji.boying.model.User;
import com.tongji.boying.model.UserOrder;
import com.tongji.boying.service.UserService;
import com.tongji.boying.service.UserTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTicketServiceImpl implements UserTicketService {
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private UserOrderMapper orderMapper;
    @Autowired
    private UserService userService;

    @Override
    public int add(int orderId, int showClassId) {
        Ticket ticket = new Ticket();
        ticket.setOrderId(orderId);
        ticket.setShowClassId(showClassId);
        ticket.setQrCodeUrl("二维码地址,后续加上");

        return ticketMapper.insert(ticket);
    }

    @Override
    public List<Ticket> list(int orderId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);//分页相关
        TicketExample ticketExample = new TicketExample();
        ticketExample.createCriteria().andOrderIdEqualTo(orderId);
        return ticketMapper.selectByExample(ticketExample);
    }

    @Override
    public Ticket getItem(int id) {
        Ticket ticket = ticketMapper.selectByPrimaryKey(id);
        if (ticket == null) {
            Asserts.fail("当前用户无此票");
        }
        //不能查询不是这个人的票
        UserOrder order = orderMapper.selectByPrimaryKey(ticket.getOrderId());
        User currentUser = userService.getCurrentUser();
        if (!currentUser.getUserId().equals(order.getUserId())) {
            Asserts.fail("不能查询其他用户的票!");
        }
        return ticket;
    }
}
