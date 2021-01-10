package com.tongji.boying.controller;


import cn.hutool.core.util.ObjectUtil;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.model.BoyingOrder;
import com.tongji.boying.service.NumsOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Api(tags = "NumsOrderController", description = "后台订单管理")
@RequestMapping("/order")
public class NumsOrderController {
    @Autowired
    private NumsOrderService numsOrderService;

    @ApiOperation("查看所有用户订单")
    @RequestMapping(value = "/listOrders", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult listOrders() {
        List<BoyingOrder> ordersList = numsOrderService.listOrders();
        if (ObjectUtil.isEmpty(ordersList)) return CommonResult.failed("不存在任何订单");
        return CommonResult.success(ordersList);
    }

    @ApiOperation("删除某id订单")
    @RequestMapping(value = "/deleteOrder/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteOrder(@PathVariable Integer id) {
        numsOrderService.deleteOrder(id);
        return CommonResult.failed("删除订单成功!");
    }

    @ApiOperation("恢复某id订单")
    @RequestMapping(value = "/recoverOrder/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult recoverOrder(@PathVariable Integer id) {
        numsOrderService.recoverOrder(id);
        return CommonResult.failed("恢复订单成功!");
    }
}
