package com.tongji.boying.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.orderParam.TicketParam;
import com.tongji.boying.mapper.BoyingTicketMapper;
import com.tongji.boying.model.BoyingTicketExample;
import com.tongji.boying.model.BoyingTicket;
import com.tongji.boying.service.NumsTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NumsTicketServiceImpl implements NumsTicketService {

    @Autowired
    private BoyingTicketMapper boyingTicketMapper;

    @Override
    public List<BoyingTicket> listTickets(TicketParam param) {
        BoyingTicketExample boyingTicketExample = new BoyingTicketExample();
        BoyingTicketExample.Criteria criteria = boyingTicketExample.createCriteria();

        //根据演出座次Id搜索票
        if (param.getSeatId() != null && param.getSeatId() != 0) {
            criteria.andSeatIdEqualTo(param.getSeatId());
        }
        //根据订单Id搜索票
        if (param.getOrderId() != null && param.getOrderId() != 0) {
            criteria.andOrderIdEqualTo(param.getOrderId());
        }

        Integer pageNum = param.getPageNum();
        if (pageNum == null || pageNum == 0) pageNum = 0;
        Integer pageSize = param.getPageSize();
        if (pageSize == null || pageSize == 0) pageSize = 5;

        PageHelper.startPage(pageNum, pageSize);
        List<BoyingTicket> boyingTickets = boyingTicketMapper.selectByExample(boyingTicketExample);
        if (ObjectUtil.isEmpty(boyingTickets)) Asserts.fail("不存在任何订单");
        return boyingTickets;
    }
}
