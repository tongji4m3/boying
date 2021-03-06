package com.tongji.boying.service.impl;

import com.github.pagehelper.PageHelper;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.orderParam.GetOrdersParam;
import com.tongji.boying.dto.orderParam.UserOrderParam;
import com.tongji.boying.mapper.BoyingOrderMapper;
import com.tongji.boying.model.BoyingOrder;
import com.tongji.boying.model.BoyingSeatModel;
import com.tongji.boying.model.BoyingUser;
import com.tongji.boying.service.BoyingSeatService;
import com.tongji.boying.service.BoyingOrderService;
import com.tongji.boying.service.BoyingUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BoyingOrderServiceImpl implements BoyingOrderService {
    @Autowired
    private BoyingOrderMapper boyingOrderMapper;
    @Autowired
    private BoyingUserService boyingUserService;

    @Autowired
    private BoyingSeatService boyingSeatService;

    @Override
    public void add(UserOrderParam param) {
        //获取参数
        Integer showId = param.getShowId();
        Integer seatId = param.getSeatId();
        Integer ticketCount = param.getCount();
        String payment = param.getPayment();
        // 若promoId不为0，则是秒杀座次价格
        Integer promoId = param.getPromoId();

        //当前用户
        BoyingUser user = boyingUserService.getCurrentUser();

        //对showId,payment做校验
        BoyingSeatModel seatModel = boyingSeatService.getShowSeat(seatId);
        if (seatModel == null) {
            Asserts.fail("演出座次不存在！");
        }
        if (!seatModel.getShowId().equals(showId)) {
            Asserts.fail("该演出座次不属于该演出！");
        }

        //查看当前用户该演出是否下单
        //已退票的不算
        Integer orderCount = boyingOrderMapper.selectByShowIdUserId(user.getId(), showId);
        if (orderCount != null && orderCount != 0) {
            //该用户已经下过单了,不能继续了
            Asserts.fail("您已经对该演出下单过了,不能重复下单!");
        }

        Double ticketPrice = seatModel.getPrice();


        if (promoId == null) promoId = 0;
        //校验活动信息
        if (promoId != 0) {
            //校验传入的活动是否对应于这个座次
            if (seatModel.getBoyingPromoModel()==null || !promoId.equals(seatModel.getBoyingPromoModel().getId())) {
                Asserts.fail("活动信息不正确");
                //校验活动是否正在进行中
            } else if (seatModel.getBoyingPromoModel().getStatus() != 2) {
                Asserts.fail("活动信息还未开始");
            }
            ticketPrice = seatModel.getBoyingPromoModel().getPrice();
        }

        //查看库存状态 并减库存
        Integer updateCount = boyingSeatService.decreaseStock(seatId, ticketCount);
        if (updateCount == 0) {
            Asserts.fail("库存不足！");
        }


        //生成订单
        BoyingOrder order = new BoyingOrder();
        order.setUserId(user.getId());
        order.setShowId(showId);
        order.setSeatId(seatId);
        order.setPromoId(promoId);
        order.setStatus(1);//待观看状态
        order.setTime(new Date());
        order.setUserDelete(false);
        order.setTicketCount(ticketCount);
        order.setPayment(payment);
        order.setTicketPrice(ticketPrice);
        order.setOrderPrice(ticketPrice * ticketCount);
        order.setQrCodeUrl("二维码");
        boyingOrderMapper.insertSelective(order);
    }

    @Override
    public void delete(int id) {
        BoyingOrder order = boyingOrderMapper.selectByPrimaryKey(id);

        if (order.getAdminDelete()) {
            Asserts.fail("管理员已删除此订单！如有疑惑，请联系客服！");
        }

        if (order.getStatus() == 1) {
            Asserts.fail("待观看订单不能删除！");
        }
        order.setUserDelete(true);
        boyingOrderMapper.updateByPrimaryKeySelective(order);
    }


    @Override
    public void cancel(int id) {
        BoyingOrder order = boyingOrderMapper.selectByPrimaryKey(id);

        if (order.getAdminDelete()) {
            Asserts.fail("管理员已删除此订单！如有疑惑，请联系客服！");
        }

        if (order.getStatus() != 1) {
            Asserts.fail("只能取消待观看订单!");
        }

        //更新订单的信息
        order.setStatus(3);
        boyingOrderMapper.updateByPrimaryKeySelective(order);

        //获取对应的演出座次,增加库存
        boyingSeatService.increaseStock(order.getSeatId(), order.getTicketCount());
    }


    @Override
    public void finish(int id) {
        BoyingOrder order = boyingOrderMapper.selectByPrimaryKey(id);

        if (order.getAdminDelete()) {
            Asserts.fail("管理员已删除此订单！如有疑惑，请联系客服！");
        }
        //更新订单的信息
        //变成已完成状态
        order.setStatus(2);
        boyingOrderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public BoyingOrder getItem(int id) {
        BoyingOrder order = boyingOrderMapper.selectByPrimaryKey(id);
        if (order.getAdminDelete()) {
            Asserts.fail("管理员已删除此订单！如有疑惑，请联系客服！");
        }
        return order;
    }

    @Override
    public List<BoyingOrder> list(GetOrdersParam param) {
        Integer pageNum = param.getPageNum();
        Integer pageSize = param.getPageSize();
        if (pageNum == null || pageNum == 0) pageNum = 1;
        if (pageSize == null || pageSize == 0) pageSize = 5;

        BoyingUser user = boyingUserService.getCurrentUser();

        Map<String, Object> map = new HashMap<>();
        map.put("name", param.getName());
        map.put("status", param.getStatus());
        map.put("userId", user.getId());

        PageHelper.startPage(pageNum, pageSize);//分页相关
        List<BoyingOrder> boyingOrders = boyingOrderMapper.selectByCondition(map);
        if (boyingOrders == null || boyingOrders.isEmpty()) {
            Asserts.fail("查询的订单不存在！");
        }
        return boyingOrders;
    }
}
