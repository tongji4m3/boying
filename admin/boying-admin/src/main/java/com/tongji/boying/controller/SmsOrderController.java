package com.tongji.boying.controller;


import com.tongji.boying.common.api.CommonPage;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.dto.SmsGetOrdersParam;
import com.tongji.boying.model.BoyingOrder;
import com.tongji.boying.service.SmsOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 后台订单管理
 */
@Controller
@Api(tags = "SmsOrderController", description = "后台订单管理")
@RequestMapping("/order")
public class SmsOrderController {
    @Autowired
    private SmsOrderService smsOrderService;

    @ApiOperation("显示所有订单")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<BoyingOrder>> list(@Validated @RequestBody SmsGetOrdersParam param) {
        return CommonResult.success(CommonPage.restPage(smsOrderService.list(param)));
    }

    @ApiOperation("获取订单详情")
    @RequestMapping(value = "details/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<BoyingOrder> getItem(@PathVariable int id) {
        return CommonResult.success(smsOrderService.getItem(id));
    }

    @ApiOperation("删除订单")
    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> deleteItem(@PathVariable int id) {
        smsOrderService.deleteItem(id);
        return CommonResult.success("删除订单成功!");
    }

    @ApiOperation("恢复订单")
    @RequestMapping(value = "recover/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> recoverItem(@PathVariable int id) {
        smsOrderService.recoverItem(id);
        return CommonResult.success("恢复订单成功!");
    }
}
