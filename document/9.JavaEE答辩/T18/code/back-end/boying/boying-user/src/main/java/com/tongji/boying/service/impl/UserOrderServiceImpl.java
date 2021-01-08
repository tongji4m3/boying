package com.tongji.boying.service.impl;

import com.github.pagehelper.PageHelper;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.UserOrderParam;
import com.tongji.boying.mapper.*;
import com.tongji.boying.model.*;
import com.tongji.boying.service.UserOrderService;
import com.tongji.boying.service.UserService;
import com.tongji.boying.service.UserTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserOrderServiceImpl implements UserOrderService {
    @Autowired
    private UserOrderMapper orderMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserTicketService userTicketService;

    @Autowired
    private ShowSessionMapper showSessionMapper;
    @Autowired
    private BoyingShowMapper showMapper;
    @Autowired
    private ShowClassMapper showClassMapper;
    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public int add(UserOrderParam param) {
        Integer showSessionId = param.getShowSessionId();
//        Integer frequentId = param.getFrequentId();
        List<Integer> showClassIds = param.getShowClassIds();

        User user = userService.getCurrentUser();
        UserOrderExample orderExample = new UserOrderExample();
        //已退票的不算
        orderExample.createCriteria().andUserIdEqualTo(user.getUserId()).andShowSessionIdEqualTo(showSessionId).andStatusNotEqualTo(3);
        List<UserOrder> orders = orderMapper.selectByExample(orderExample);
        if (!orders.isEmpty()) {
            //该用户已经下过单了,不能继续了
            Asserts.fail("您已经对该场次下单过了,不能重复下单!");
        }
        if (showClassIds.size() == 0) {
            Asserts.fail("一个订单至少要有1张票!");
        }
        if (showClassIds.size() > 5) {
            Asserts.fail("一个订单最多只能有5张票!");
        }

//        if (frequentService.getItem(frequentId) == null)
//        {
//            Asserts.fail("订单中的常用购票人不合法!");
//        }

        ShowSession showSession = showSessionMapper.selectByPrimaryKey(showSessionId);
        if (showSession == null) {
            Asserts.fail("演出场次选择不合法!");
        }
        ShowClassExample showClassExample = new ShowClassExample();
        showClassExample.createCriteria().andShowSessionIdEqualTo(showSession.getShowSessionId());
        //数据库中的座次信息
        List<ShowClass> dbShowClasses = showClassMapper.selectByExample(showClassExample);
        //查看库存状态
        for (ShowClass dbShowClass : dbShowClasses) {
            if (dbShowClass.getStock() <= 0) {
                //只要有一个库存没有，则告知不合法
                Asserts.fail("库存不足！");
            }
        }

        List<Integer> dbShowClassIds = dbShowClasses.stream()
                .map(ShowClass::getShowClassId)
                .collect(Collectors.toList());
        //校验座次是否合法
        for (Integer showClassId : showClassIds) {
            if (!dbShowClassIds.contains(showClassId)) {
                Asserts.fail("演出座次选择不合法!");
            }
        }

        //减库存
        for (Integer showClassId : showClassIds) {
            ShowClass dbShowClass = showClassMapper.selectByPrimaryKey(showClassId);
            dbShowClass.setStock(dbShowClass.getStock() - 1);
            showClassMapper.updateByPrimaryKeySelective(dbShowClass);
        }

        //生成订单
        UserOrder order = new UserOrder();
        order.setUserId(user.getUserId());
        order.setShowSessionId(showSessionId);
        order.setAddressId(null);//0约定为不邮寄
//        order.setFrequentId(frequentId);
        order.setFrequentId(user.getUserId());
        order.setStatus(1);//待观看状态
        order.setTime(new Date());
        order.setPayment(param.getPayment());
        order.setUserDelete(false);
        order.setShowId(showSession.getShowId());


        //订单总数,订单总金额
        double totalMoney = 0;
        int count = 0;

        for (Integer showClassId : showClassIds) {
            ShowClass showClass = showClassMapper.selectByPrimaryKey(showClassId);


            totalMoney += showClass.getPrice();
            ++count;
        }
        order.setTicketCount(count);
        order.setMoney(totalMoney);
        int insert = orderMapper.insert(order);
        /*
        如果报错,可能是重新生成了generator
        在orderMapper.insert(order)的mapper里面改成如下:
        <insert id="insert" parameterType="com.tongji.boying.model.UserOrder" keyProperty="orderId"
          keyColumn="order_id" useGeneratedKeys="true">
         */
        //生成票
        for (Integer showClassId : showClassIds) {
            System.out.println(order.getOrderId() + "   " + showClassId);
            userTicketService.add(order.getOrderId(), showClassId);
        }

        return insert;
    }


    @Override
    public int delete(int id) {
        User user = userService.getCurrentUser();
        UserOrderExample userOrderExample = new UserOrderExample();
        userOrderExample.createCriteria().andUserIdEqualTo(user.getUserId()).andOrderIdEqualTo(id).andUserDeleteEqualTo(false);
        List<UserOrder> userOrders = orderMapper.selectByExample(userOrderExample);
        if (userOrders.isEmpty()) {
            Asserts.fail("无此订单");
        }
        UserOrder order = new UserOrder();
        order.setUserDelete(true);
        order.setOrderId(userOrders.get(0).getOrderId());
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public int cancel(int id) {
        User user = userService.getCurrentUser();
        UserOrderExample userOrderExample = new UserOrderExample();
        userOrderExample.createCriteria().andUserIdEqualTo(user.getUserId()).andOrderIdEqualTo(id).andUserDeleteEqualTo(false);
        List<UserOrder> userOrders = orderMapper.selectByExample(userOrderExample);
        if (userOrders.isEmpty()) {
            Asserts.fail("无此订单");
        }
        if (userOrders.get(0).getStatus() == 2) {
            Asserts.fail("不能取消已完成订单!");
        }

        UserOrder order = new UserOrder();
        //增加库存
        ShowClassExample showClassExample = new ShowClassExample();
        showClassExample.createCriteria().andShowSessionIdEqualTo(order.getShowSessionId());
        List<ShowClass> showClasses = showClassMapper.selectByExample(showClassExample);
        if (showClasses != null && showClasses.size() != 0) {
            for (ShowClass showClass : showClasses) {
                showClass.setStock(showClass.getStock() + 1);
                showClassMapper.updateByPrimaryKeySelective(showClass);
            }
        }

        order.setOrderId(userOrders.get(0).getOrderId());
        order.setStatus(3);
        TicketExample ticketExample = new TicketExample();
        ticketExample.createCriteria().andOrderIdEqualTo(order.getOrderId());
        ticketMapper.deleteByExample(ticketExample);
//        return orderMapper.deleteByPrimaryKey(order.getOrderId());
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public List<UserOrder> list(Integer status, Integer pageNum, Integer pageSize) {
        User user = userService.getCurrentUser();
        UserOrderExample userOrderExample = new UserOrderExample();
        UserOrderExample.Criteria criteria = userOrderExample.createCriteria();
        if (status != null && status != -1) {
            criteria.andStatusEqualTo(status);
        }
        criteria.andUserIdEqualTo(user.getUserId()).andUserDeleteEqualTo(false);
        PageHelper.startPage(pageNum, pageSize);//分页相关
        return orderMapper.selectByExample(userOrderExample);
    }

    @Override
    public UserOrder getItem(int id) {
        User user = userService.getCurrentUser();
        UserOrderExample userOrderExample = new UserOrderExample();
        userOrderExample.createCriteria().andUserIdEqualTo(user.getUserId()).andOrderIdEqualTo(id).andUserDeleteEqualTo(false);
        List<UserOrder> userOrders = orderMapper.selectByExample(userOrderExample);
        if (!CollectionUtils.isEmpty(userOrders)) {
            return userOrders.get(0);
        }
        return null;
    }
}
