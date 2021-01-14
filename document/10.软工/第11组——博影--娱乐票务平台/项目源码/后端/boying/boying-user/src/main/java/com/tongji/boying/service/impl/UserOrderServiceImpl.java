package com.tongji.boying.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.orderParam.GetOrdersParam;
import com.tongji.boying.dto.orderParam.UserOrderParam;
import com.tongji.boying.mapper.BoyingOrderMapper;
import com.tongji.boying.mapper.BoyingSeatMapper;
import com.tongji.boying.mapper.BoyingShowMapper;
import com.tongji.boying.mapper.BoyingTicketMapper;
import com.tongji.boying.model.*;
import com.tongji.boying.service.UserOrderService;
import com.tongji.boying.service.UserService;
import com.tongji.boying.service.UserTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserOrderServiceImpl implements UserOrderService {
    @Autowired
    private BoyingOrderMapper orderMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserTicketService userTicketService;

    @Autowired
    private BoyingSeatMapper boyingSeatMapper;
    @Autowired
    private BoyingTicketMapper ticketMapper;
    @Autowired
    private BoyingShowMapper showMapper;

    @Override
    public void add(UserOrderParam param) {
        Integer showId = param.getShowId();
        List<Integer> showSeatIds = param.getShowSeatIds();
        Map<Integer, Integer> seatMap = new HashMap<>();
        for (Integer showSeatId : showSeatIds) {
            seatMap.put(showSeatId, seatMap.getOrDefault(showSeatId, 0) + 1);
        }

        BoyingUser user = userService.getCurrentUser();

        BoyingOrderExample orderExample = new BoyingOrderExample();
        orderExample.createCriteria().andUserIdEqualTo(user.getId()).andShowIdEqualTo(showId).andStatusNotEqualTo(3);
        List<BoyingOrder> orders = orderMapper.selectByExample(orderExample);
        if (!orders.isEmpty()) {
            Asserts.fail("您已经对该演出下单过了,不能重复下单!");
        }
        if (showSeatIds.size() == 0) {
            Asserts.fail("一个订单至少要有1张票!");
        }
        if (showSeatIds.size() > 5) {
            Asserts.fail("一个订单最多只能有5张票!");
        }

        BoyingShow boyingShow = showMapper.selectByPrimaryKey(showId);
        if (boyingShow == null) {
            Asserts.fail("演出选择不合法!");
        }


        BoyingSeatExample boyingSeatExample = new BoyingSeatExample();
        boyingSeatExample.createCriteria().andShowIdEqualTo(boyingShow.getId());
        List<BoyingSeat> dbShowSeats = boyingSeatMapper.selectByExample(boyingSeatExample);

        List<Integer> dbShowClassIds = dbShowSeats.stream()
                .map(BoyingSeat::getId)
                .collect(Collectors.toList());
        for (Integer showSeatId : showSeatIds) {
            if (!dbShowClassIds.contains(showSeatId)) {
                Asserts.fail("演出座次选择不合法!");
            }
        }

        for (Map.Entry<Integer, Integer> entry : seatMap.entrySet()) {
            BoyingSeat boyingSeat = boyingSeatMapper.selectByPrimaryKey(entry.getKey());
            if (boyingSeat.getStock() < entry.getValue()) {
                Asserts.fail("库存不足！");
            }
        }

        double totalMoney = 0;
        int count = 0;

        for (Map.Entry<Integer, Integer> entry : seatMap.entrySet()) {
            BoyingSeat boyingSeat = boyingSeatMapper.selectByPrimaryKey(entry.getKey());

            totalMoney += boyingSeat.getPrice() * entry.getValue();
            count += entry.getValue();

            boyingSeat.setStock(boyingSeat.getStock() - entry.getValue());
            boyingSeatMapper.updateByPrimaryKeySelective(boyingSeat);
        }

        BoyingOrder order = new BoyingOrder();
        order.setUserId(user.getId());
        order.setShowId(showId);
        order.setStatus(1);
        order.setTime(new Date());
        order.setUserDelete(false);
        order.setTicketCount(count);
        order.setPayment(param.getPayment());

        order.setMoney(totalMoney);
        int insert = orderMapper.insertSelective(order);
        for (Integer showSeatId : showSeatIds) {
            System.out.println(order.getId() + "   " + showSeatId);
            userTicketService.add(order.getId(), showSeatId);
        }
    }


    @Override
    public void delete(int id) {
        BoyingUser user = userService.getCurrentUser();
        BoyingOrderExample userOrderExample = new BoyingOrderExample();
        userOrderExample.createCriteria().andUserIdEqualTo(user.getId()).andIdEqualTo(id).andUserDeleteEqualTo(false);
        List<BoyingOrder> userOrders = orderMapper.selectByExample(userOrderExample);
        if (userOrders == null || userOrders.isEmpty()) {
            Asserts.fail("无此订单");
        }
        BoyingOrder boyingOrder = userOrders.get(0);
        if (boyingOrder.getStatus() == 1) {
            Asserts.fail("待观看订单不能删除！");
        }
        if (boyingOrder.getAdminDelete()) {
            Asserts.fail("管理员已删除此订单！如有疑惑，请联系客服！");
        }
        boyingOrder.setUserDelete(true);
        orderMapper.updateByPrimaryKeySelective(boyingOrder);
    }

    @Override
    public void cancel(int id) {
        BoyingUser user = userService.getCurrentUser();
        BoyingOrderExample userOrderExample = new BoyingOrderExample();
        userOrderExample.createCriteria().andUserIdEqualTo(user.getId()).andIdEqualTo(id).andUserDeleteEqualTo(false);
        List<BoyingOrder> userOrders = orderMapper.selectByExample(userOrderExample);
        if (userOrders.isEmpty()) {
            Asserts.fail("无此订单");
        }
        if (userOrders.get(0).getStatus() != 1) {
            Asserts.fail("只能取消待观看订单!");
        }

        BoyingOrder order = userOrders.get(0);
        order.setStatus(3);
        orderMapper.updateByPrimaryKeySelective(order);

        BoyingTicketExample boyingTicketExample = new BoyingTicketExample();
        boyingTicketExample.createCriteria().andOrderIdEqualTo(order.getId());
        List<BoyingTicket> boyingTickets = ticketMapper.selectByExample(boyingTicketExample);
        for (BoyingTicket boyingTicket : boyingTickets) {
            BoyingSeat boyingSeat = boyingSeatMapper.selectByPrimaryKey(boyingTicket.getSeatId());
            boyingSeat.setStock(boyingSeat.getStock() + 1);
            boyingSeatMapper.updateByPrimaryKeySelective(boyingSeat);
            ticketMapper.deleteByPrimaryKey(boyingTicket.getId());
        }
    }

    @Override
    public List<BoyingOrder> list(GetOrdersParam param) {
        Integer status = param.getStatus();
        Integer pageNum = param.getPageNum();
        Integer pageSize = param.getPageSize();
        String name = param.getName();


        if (pageNum == null || pageNum == 0) pageNum = 0;
        if (pageSize == null || pageSize == 0) pageSize = 5;

        BoyingUser user = userService.getCurrentUser();
        BoyingOrderExample userOrderExample = new BoyingOrderExample();
        BoyingOrderExample.Criteria criteria = userOrderExample.createCriteria();

        if (status != null && status != 0) {
            criteria.andStatusEqualTo(status);
        }
        criteria.andUserIdEqualTo(user.getId()).andUserDeleteEqualTo(false);
        if (!StrUtil.isEmpty(name)) {
            BoyingShowExample boyingShowExample = new BoyingShowExample();
            boyingShowExample.createCriteria().andNameLike("%" + name + "%");
            List<BoyingShow> boyingShows = showMapper.selectByExample(boyingShowExample);

            if (boyingShows == null || boyingShows.size() == 0) {
                Asserts.fail("查询不到相关的订单！");
            }
            List<Integer> showIds = new LinkedList<>();
            for (BoyingShow boyingShow : boyingShows) {
                showIds.add(boyingShow.getId());
            }
            criteria.andShowIdIn(showIds);

        }

        PageHelper.startPage(pageNum, pageSize);
        List<BoyingOrder> boyingOrders = orderMapper.selectByExample(userOrderExample);
        if (boyingOrders == null || boyingOrders.isEmpty()) {
            Asserts.fail("查询的订单不存在！");
        }
        return boyingOrders;
    }

    @Override
    public BoyingOrder getItem(int id) {
        BoyingUser user = userService.getCurrentUser();
        BoyingOrderExample userOrderExample = new BoyingOrderExample();
        userOrderExample.createCriteria().andUserIdEqualTo(user.getId()).andIdEqualTo(id).andUserDeleteEqualTo(false);
        List<BoyingOrder> userOrders = orderMapper.selectByExample(userOrderExample);
        if (CollectionUtils.isEmpty(userOrders)) {
            Asserts.fail("该订单不存在！");
        }
        if (userOrders.get(0).getAdminDelete()) {
            Asserts.fail("管理员已删除此订单！如有疑惑，请联系客服！");
        }
        return userOrders.get(0);
    }
}
