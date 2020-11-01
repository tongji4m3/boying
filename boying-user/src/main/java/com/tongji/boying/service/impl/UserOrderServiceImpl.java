package com.tongji.boying.service.impl;

import com.github.pagehelper.PageHelper;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.UserOrderParam;
import com.tongji.boying.mapper.UserOrderMapper;
import com.tongji.boying.mapper.ShowClassMapper;
import com.tongji.boying.mapper.ShowMapper;
import com.tongji.boying.mapper.ShowSessionMapper;
import com.tongji.boying.model.*;
import com.tongji.boying.service.UserOrderService;
import com.tongji.boying.service.UserTicketService;
import com.tongji.boying.service.UserFrequentService;
import com.tongji.boying.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserOrderServiceImpl implements UserOrderService
{
    @Autowired
    private UserOrderMapper orderMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserTicketService userTicketService;
    @Autowired
    private UserFrequentService frequentService;

    @Autowired
    private ShowSessionMapper showSessionMapper;
    @Autowired
    private ShowMapper showMapper;
    @Autowired
    private ShowClassMapper showClassMapper;

    @Override
    public int add(UserOrderParam param)
    {
        Integer showSessionId = param.getShowSessionId();
        Integer frequentId = param.getFrequentId();
        List<Integer> showClassIds = param.getShowClassIds();

        User user = userService.getCurrentUser();
        UserOrderExample orderExample = new UserOrderExample();
        orderExample.createCriteria().andUserIdEqualTo(user.getUserId()).andShowSessionIdEqualTo(showSessionId);
        List<UserOrder> orders = orderMapper.selectByExample(orderExample);
        if(!orders.isEmpty())
        {
            //该用户已经下过单了,不能继续了
            Asserts.fail("您已经对该场次下单过了,不能重复下单!");
        }
        if(showClassIds.size()==0)
        {
            Asserts.fail("一个订单至少要有1张票!");
        }
        if(showClassIds.size()>5)
        {
            Asserts.fail("一个订单最多只能有5张票!");
        }

        if(frequentService.getItem(frequentId)==null)
        {
            Asserts.fail("订单中的常用购票人不合法!");
        }

        ShowSession showSession = showSessionMapper.selectByPrimaryKey(showSessionId);
        if(showSession==null)
        {
            Asserts.fail("演出场次选择不合法!");
        }
        ShowClassExample showClassExample = new ShowClassExample();
        showClassExample.createCriteria().andShowSessionIdEqualTo(showSession.getShowSessionId());
        //数据库中的座次信息
        List<ShowClass> dbShowClasses = showClassMapper.selectByExample(showClassExample);
        List<Integer> dbShowClassIds = dbShowClasses.stream()
                .map(ShowClass::getShowClassId)
                .collect(Collectors.toList());
        //校验座次是否合法
        for (Integer showClassId : showClassIds)
        {
            if(!dbShowClassIds.contains(showClassId))
            {
                Asserts.fail("演出座次选择不合法!");
            }
        }

        Show show = showMapper.selectByPrimaryKey(showSession.getShowId());


        //生成订单
        UserOrder order = new UserOrder();
        order.setUserId(user.getUserId());
        order.setShowSessionId(showSessionId);
        order.setAddressId(0);//0约定为不邮寄
        order.setFrequentId(frequentId);
        order.setStatus(1);//待评价状态
        order.setTime(new Date());
        order.setPayment("支付宝");
        order.setUserDelete(false);
        order.setShowName(show.getName());
        order.setShowAddress(show.getAddress());
        order.setShowTime(showSession.getStartTime());


        //订单总数,订单总金额
        double totalMoney = 0;
        int count = 0;

        for (Integer showClassId : showClassIds)
        {
            ShowClass showClass = showClassMapper.selectByPrimaryKey(showClassId);
            //生成票
            userTicketService.add(1,showClassId);

            totalMoney += showClass.getPrice();
            ++count;
        }
        order.setTicketCount(count);
        order.setMoney(totalMoney);
        orderMapper.insert(order);
        System.out.println(order);


        return 0;
    }

    @Override
    public int delete(int id)
    {
        User user = userService.getCurrentUser();
        UserOrderExample userOrderExample = new UserOrderExample();
        userOrderExample.createCriteria().andUserIdEqualTo(user.getUserId()).andOrderIdEqualTo(id);
        List<UserOrder> userOrders = orderMapper.selectByExample(userOrderExample);
        if(userOrders.isEmpty())
        {
            Asserts.fail("无此订单");
        }
        UserOrder order = new UserOrder();
        order.setUserDelete(true);
        order.setOrderId(userOrders.get(0).getOrderId());
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public List<UserOrder> list(Integer pageNum, Integer pageSize)
    {
        PageHelper.startPage(pageNum, pageSize);//分页相关
        User user = userService.getCurrentUser();
        UserOrderExample userOrderExample = new UserOrderExample();
        userOrderExample.createCriteria().andUserIdEqualTo(user.getUserId()).andUserDeleteEqualTo(false);
        return orderMapper.selectByExample(userOrderExample);
    }

    @Override
    public UserOrder getItem(int id)
    {
        User user = userService.getCurrentUser();
        UserOrderExample userOrderExample = new UserOrderExample();
        userOrderExample.createCriteria().andUserIdEqualTo(user.getUserId()).andOrderIdEqualTo(id);
        List<UserOrder> userOrders = orderMapper.selectByExample(userOrderExample);
        if (!CollectionUtils.isEmpty(userOrders))
        {
            return userOrders.get(0);
        }
        return null;
    }
}
