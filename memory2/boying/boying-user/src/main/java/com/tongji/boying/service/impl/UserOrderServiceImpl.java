package com.tongji.boying.service.impl;

import com.github.pagehelper.PageHelper;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.orderParam.GetOrdersParam;
import com.tongji.boying.dto.orderParam.UserOrderParam;
import com.tongji.boying.mapper.BoyingOrderMapper;
import com.tongji.boying.mapper.BoyingSeatMapper;
import com.tongji.boying.mapper.BoyingShowMapper;
import com.tongji.boying.mapper.BoyingTicketMapper;
import com.tongji.boying.model.BoyingOrder;
import com.tongji.boying.model.BoyingSeat;
import com.tongji.boying.model.BoyingTicket;
import com.tongji.boying.model.BoyingUser;
import com.tongji.boying.service.UserOrderService;
import com.tongji.boying.service.UserService;
import com.tongji.boying.service.UserTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public void generate(Integer count) {
        Random random = new Random();
        //从这些演出中下单
//        List<Integer> showIds = showMapper.selectIdList();
        //下单count次
        for (int i = 0; i < count; i++) {
            //[1,1000] 随机下单用户
            Integer userId = random.nextInt(1000) + 1;

            //随机一个演出
//            Integer showId = showIds.get(random.nextInt(showIds.size()));
//            Integer showId = random.nextInt(10000000) + 1;
            Integer showId = random.nextInt(1000) + 1;

            //查看当前用户该演出是否下单
            Map<String, Integer> map = new HashMap<>();
            map.put("userId", userId);
            map.put("showId", showId);
            //已退票的不算
            Integer orderCount = orderMapper.selectByShowIdUserId(map);
            if (orderCount != null && orderCount != 0) {
                //该用户已经下过单了,不能继续了
                continue;
            }

            //找该演出的座次
            List<BoyingSeat> boyingSeats = boyingSeatMapper.selectList(showId);
            int seatDecrease = 10;
            //要买的座次Id
            int seatIndex = 0;
            int seatId = 0;

            while (true) {
                seatDecrease--;
                //随机对一个座次修改
                seatIndex = random.nextInt(boyingSeats.size());
                seatId = boyingSeats.get(seatIndex).getId();
                //检验座次，并减库存
                int success = boyingSeatMapper.decreaseStock(seatId);
                //如果成功买到了票，就不继续减库存了 、  或者都没票了
                if (success > 0 || seatDecrease == 0) {
                    break;
                }
            }
            if (seatDecrease == 0) {
                //没能找到有库存的，下一个
                continue;
            }


            //生成订单
            BoyingOrder order = new BoyingOrder();


            //随机支付方式
            int randomPayment = random.nextInt(2);
            if (randomPayment % 2 == 0) {
                order.setPayment("微信支付");
            }
            else {
                order.setPayment("支付宝");
            }


            order.setUserId(userId);
            order.setShowId(showId);
            order.setStatus(1);//待观看状态
            order.setTime(new Date());
            order.setUserDelete(0);
            order.setAdminDelete(0);
            //只下单一个
            order.setTicketCount(1);

            order.setMoney(boyingSeats.get(seatIndex).getPrice());

            orderMapper.insertSelective(order);

            //生成票
            userTicketService.add(order.getId(), seatId);
        }
    }

    @Override
    public void add(UserOrderParam param) {
        //获取参数
        Integer showId = param.getShowId();
        List<Integer> showSeatIds = param.getShowSeatIds();

        if (showSeatIds.size() == 0) {
            Asserts.fail("一个订单至少要有1张票!");
        }
        if (showSeatIds.size() > 5) {
            Asserts.fail("一个订单最多只能有5张票!");
        }

        //存储的是(座次Id，count)
        Map<Integer, Integer> seatMap = new HashMap<>();
        for (Integer showSeatId : showSeatIds) {
            seatMap.put(showSeatId, seatMap.getOrDefault(showSeatId, 0) + 1);
        }

        //当前用户
        BoyingUser user = userService.getCurrentUser();

        //查看当前用户该演出是否下单
        Map<String, Integer> map = new HashMap<>();
        map.put("userId", user.getId());
        map.put("showId", showId);
        //已退票的不算
        Integer orderCount = orderMapper.selectByShowIdUserId(map);
        if (orderCount != null && orderCount != 0) {
            //该用户已经下过单了,不能继续了
            Asserts.fail("您已经对该演出下单过了,不能重复下单!");
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
            count += entry.getValue();

            boyingSeat.setStock(boyingSeat.getStock() - entry.getValue());
            boyingSeatMapper.updateByPrimaryKeySelective(boyingSeat);
        }

        //生成订单
        BoyingOrder order = new BoyingOrder();
        order.setUserId(user.getId());
        order.setShowId(showId);
        order.setStatus(1);//待观看状态
        order.setTime(new Date());
        order.setUserDelete(0);
        order.setTicketCount(count);
        order.setPayment(param.getPayment());

        order.setMoney(totalMoney);
        orderMapper.insertSelective(order);
        //生成票
        for (Integer showSeatId : showSeatIds) {
            System.out.println(order.getId() + "   " + showSeatId);
            userTicketService.add(order.getId(), showSeatId);
        }
    }

    @Override
    public void delete(int id) {
        BoyingUser user = userService.getCurrentUser();

        BoyingOrder order = orderMapper.selectByPrimaryKey(id);
        if (order == null) {
            Asserts.fail("该订单不存在！");
        }

        if (order.getAdminDelete() == 1) {
            Asserts.fail("管理员已删除此订单！如有疑惑，请联系客服！");
        }

        if (order.getStatus() == 1) {
            Asserts.fail("待观看订单不能删除！");
        }
        order.setUserDelete(1);
        orderMapper.updateByPrimaryKeySelective(order);
    }


    @Override
    public void cancel(int id) {

        BoyingUser user = userService.getCurrentUser();

        BoyingOrder order = orderMapper.selectByPrimaryKey(id);
        if (order == null) {
            Asserts.fail("该订单不存在！");
        }

        if (order.getAdminDelete() == 1) {
            Asserts.fail("管理员已删除此订单！如有疑惑，请联系客服！");
        }

        if (order.getStatus() != 1) {
            Asserts.fail("只能取消待观看订单!");
        }

        //更新订单的信息
        order.setStatus(3);
        orderMapper.updateByPrimaryKeySelective(order);

        //获取对应的演出座次,增加库存
        List<BoyingTicket> listByOrderId = ticketMapper.getListByOrderId(order.getId());
        for (BoyingTicket boyingTicket : listByOrderId) {
            boyingSeatMapper.updateSeatsStock(boyingTicket.getSeatId());
        }

        //删除对应的票
        ticketMapper.deleteTicketsList(order.getId());
    }


    @Override
    public void finish(int id) {
        BoyingUser user = userService.getCurrentUser();

        BoyingOrder order = orderMapper.selectByPrimaryKey(id);
        if (order == null) {
            Asserts.fail("该订单不存在！");
        }

        if (order.getAdminDelete() == 1) {
            Asserts.fail("管理员已删除此订单！如有疑惑，请联系客服！");
        }

        if (order.getStatus() != 1) {
            Asserts.fail("只能取消待观看订单!");
        }
        //更新订单的信息
        //变成已完成状态
        order.setStatus(2);
        orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public List<BoyingOrder> list(GetOrdersParam param) {
        Integer pageNum = param.getPageNum();
        Integer pageSize = param.getPageSize();
        if (pageNum == null || pageNum == 0) pageNum = 1;
        if (pageSize == null || pageSize == 0) pageSize = 5;

        BoyingUser user = userService.getCurrentUser();

        String name = param.getName();
        Integer status = param.getStatus();
        Map<String, Object> map = new HashMap<>();
        map.put("name", param.getName());
        map.put("status", param.getStatus());
        map.put("userId", user.getId());

        PageHelper.startPage(pageNum, pageSize);//分页相关
        List<BoyingOrder> boyingOrders = orderMapper.selectByCondition(map);
        if (boyingOrders == null || boyingOrders.isEmpty()) {
            Asserts.fail("查询的订单不存在！");
        }
        return boyingOrders;
    }


    @Override
    public BoyingOrder getItem(int id) {
        BoyingUser user = userService.getCurrentUser();

        BoyingOrder order = orderMapper.selectByPrimaryKey(id);
        if (order == null || order.getUserDelete() == 1) {
            Asserts.fail("该订单不存在！");
        }

        if (order.getAdminDelete() == 1) {
            Asserts.fail("管理员已删除此订单！如有疑惑，请联系客服！");
        }
        return order;
    }
}
