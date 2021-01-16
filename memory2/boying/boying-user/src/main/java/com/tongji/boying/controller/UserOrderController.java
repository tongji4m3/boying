package com.tongji.boying.controller;

import com.tongji.boying.common.api.CommonPage;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.dto.orderParam.GetOrdersParam;
import com.tongji.boying.dto.orderParam.TestParam;
import com.tongji.boying.dto.orderParam.TicketReturn;
import com.tongji.boying.dto.orderParam.UserOrderParam;
import com.tongji.boying.model.BoyingOrder;
import com.tongji.boying.model.BoyingTicket;
import com.tongji.boying.service.UserOrderService;
import com.tongji.boying.service.UserTicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 订单管理Controller
 */
@Controller
@Api(tags = "UserOrderController", description = "用户模块订单相关API")
@RequestMapping("/order")
public class UserOrderController {
    @Autowired
    private UserOrderService orderService;
    @Autowired
    private UserTicketService ticketService;

    @ApiOperation("模拟高并发请求的API")
    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult generate(@Validated @RequestBody TestParam param) {
        long start = System.currentTimeMillis();
        orderService.generate(param);
        long time = System.currentTimeMillis() - start;
        return CommonResult.success(null, "需要:" + time + "s");
    }


    @ApiOperation("用户下单")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(@Validated @RequestBody UserOrderParam param) {
        orderService.add(param);
        return CommonResult.success(null, "下单成功");
    }

    @ApiOperation("删除订单")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable int id) {
        orderService.delete(id);
        return CommonResult.success(null, "删除订单成功");
    }

    @ApiOperation("取消订单")
    @RequestMapping(value = "/cancel/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult cancel(@PathVariable int id) {
        orderService.cancel(id);
        return CommonResult.success(null, "取消订单成功");
    }

    @ApiOperation("显示所有订单")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<BoyingOrder>> list(@Validated @RequestBody GetOrdersParam param) {
        return CommonResult.success(CommonPage.restPage(orderService.list(param)));
    }

    @ApiOperation("获取订单详情")
    @RequestMapping(value = "details/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<BoyingOrder> getItem(@PathVariable int id) {
        return CommonResult.success(orderService.getItem(id));
    }

    @ApiOperation("获取某订单的所有票")
    @RequestMapping(value = "/tickets/{orderId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<TicketReturn>> getShowTickets(@PathVariable Integer orderId) {
        return CommonResult.success(ticketService.list(orderId));
    }
}
