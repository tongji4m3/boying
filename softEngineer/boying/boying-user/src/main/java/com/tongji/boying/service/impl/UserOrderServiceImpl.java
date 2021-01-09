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
        //获取参数
        Integer showId = param.getShowId();
        List<Integer> showSeatIds = param.getShowSeatIds();
        //存储的是(座次Id，count)
        Map<Integer, Integer> seatMap = new HashMap<>();
        for (Integer showSeatId : showSeatIds) {
            seatMap.put(showSeatId, seatMap.getOrDefault(showSeatId, 0) + 1);
        }

        //当前用户
        BoyingUser user = userService.getCurrentUser();

        BoyingOrderExample orderExample = new BoyingOrderExample();
        //已退票的不算
        orderExample.createCriteria().andUserIdEqualTo(user.getId()).andShowIdEqualTo(showId).andStatusNotEqualTo(3);
        List<BoyingOrder> orders = orderMapper.selectByExample(orderExample);
        if (!orders.isEmpty()) {
            //该用户已经下过单了,不能继续了
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
        //数据库中的座次信息
        List<BoyingSeat> dbShowSeats = boyingSeatMapper.selectByExample(boyingSeatExample);

        List<Integer> dbShowClassIds = dbShowSeats.stream()
                .map(BoyingSeat::getId)
                .collect(Collectors.toList());
        //校验座次是否合法
        for (Integer showSeatId : showSeatIds) {
            if (!dbShowClassIds.contains(showSeatId)) {
                Asserts.fail("演出座次选择不合法!");
            }
        }

        //查看库存状态
        for (Map.Entry<Integer, Integer> entry : seatMap.entrySet()) {
            BoyingSeat boyingSeat = boyingSeatMapper.selectByPrimaryKey(entry.getKey());
            if (boyingSeat.getStock() < entry.getValue()) {
                //只要有一个库存没有，则告知不合法
                Asserts.fail("库存不足！");
            }
        }

        //订单总数,订单总金额
        double totalMoney = 0;
        int count = 0;

        //减库存
        for (Map.Entry<Integer, Integer> entry : seatMap.entrySet()) {
            BoyingSeat boyingSeat = boyingSeatMapper.selectByPrimaryKey(entry.getKey());

            totalMoney += boyingSeat.getPrice() * entry.getValue();
            count+=entry.getValue();

            boyingSeat.setStock(boyingSeat.getStock() - entry.getValue());
            boyingSeatMapper.updateByPrimaryKeySelective(boyingSeat);
        }

        //生成订单
        BoyingOrder order = new BoyingOrder();
        order.setUserId(user.getId());
        order.setShowId(showId);
        order.setStatus(1);//待观看状态
        order.setTime(new Date());
        order.setUserDelete(false);
        order.setTicketCount(count);
        order.setMoney(totalMoney);
        int insert = orderMapper.insert(order);
        //生成票
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

        //更新订单的信息
        BoyingOrder order = userOrders.get(0);
        order.setStatus(3);
        orderMapper.updateByPrimaryKeySelective(order);

        //获取对应的票
        BoyingTicketExample boyingTicketExample = new BoyingTicketExample();
        boyingTicketExample.createCriteria().andOrderIdEqualTo(order.getId());
        List<BoyingTicket> boyingTickets = ticketMapper.selectByExample(boyingTicketExample);
        for (BoyingTicket boyingTicket : boyingTickets) {
            //获取对应的演出座次,增加库存
            BoyingSeat boyingSeat = boyingSeatMapper.selectByPrimaryKey(boyingTicket.getSeatId());
            boyingSeat.setStock(boyingSeat.getStock() + 1);
            boyingSeatMapper.updateByPrimaryKeySelective(boyingSeat);
            //删除对应的票
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

        //根据演出的名称模糊查询
        if (!StrUtil.isEmpty(name)) {
            BoyingShowExample boyingShowExample = new BoyingShowExample();
            boyingShowExample.createCriteria().andNameLike("%" + name + "%");
            List<BoyingShow> boyingShows = showMapper.selectByExample(boyingShowExample);
            List<Integer> showIds = new LinkedList<>();
            for (BoyingShow boyingShow : boyingShows) {
                showIds.add(boyingShow.getId());
            }
            criteria.andShowIdIn(showIds);
        }

        if (status != null && status != 0) {
            criteria.andStatusEqualTo(status);
        }
        criteria.andUserIdEqualTo(user.getId()).andUserDeleteEqualTo(false);
        PageHelper.startPage(pageNum, pageSize);//分页相关
        return orderMapper.selectByExample(userOrderExample);
    }

    @Override
    public BoyingOrder getItem(int id) {
        BoyingUser user = userService.getCurrentUser();
        BoyingOrderExample userOrderExample = new BoyingOrderExample();
        userOrderExample.createCriteria().andUserIdEqualTo(user.getId()).andIdEqualTo(id).andUserDeleteEqualTo(false);
        List<BoyingOrder> userOrders = orderMapper.selectByExample(userOrderExample);
        if (userOrders.get(0).getAdminDelete()) {
            Asserts.fail("管理员已删除此订单！如有疑惑，请联系客服！");
        }
        if (!CollectionUtils.isEmpty(userOrders)) {
            return userOrders.get(0);
        }
        return null;
    }
}
