package com.tongji.boying.controller;

import com.tongji.boying.common.api.CommonPage;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.dto.orderParam.GetOrdersParam;
import com.tongji.boying.dto.orderParam.UserOrderParam;
import com.tongji.boying.model.BoyingOrder;
import com.tongji.boying.service.BoyingOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 订单管理Controller
 */
@Controller
@Api(tags = "BoyingOrderController", description = "用户模块订单相关API")
@RequestMapping("/order")
public class BoyingOrderController {
    @Autowired
    private BoyingOrderService boyingOrderService;

    @ApiOperation("用户下单")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> add(@Validated @RequestBody UserOrderParam param) {
        boyingOrderService.add(param);
        return CommonResult.success("下单成功");
    }

    @ApiOperation("删除订单")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> delete(@PathVariable int id) {
        boyingOrderService.delete(id);
        return CommonResult.success("删除订单成功");
    }

    @ApiOperation("取消订单")
    @RequestMapping(value = "/cancel/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> cancel(@PathVariable int id) {
        boyingOrderService.cancel(id);
        return CommonResult.success("取消订单成功");
    }

    @ApiOperation("显示所有订单")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<BoyingOrder>> list(@Validated @RequestBody GetOrdersParam param) {
        return CommonResult.success(CommonPage.restPage(boyingOrderService.list(param)));
    }

    @ApiOperation("获取订单详情")
    @RequestMapping(value = "details/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<BoyingOrder> getItem(@PathVariable int id) {
        return CommonResult.success(boyingOrderService.getItem(id));
    }

    @ApiOperation("查看该用户对某演出是否下单过")
    @RequestMapping(value = "/checkOrder/{showId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> checkOrder(Integer showId) {
        return CommonResult.success(boyingOrderService.checkOrder(showId));
    }
}
