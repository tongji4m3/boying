package com.tongji.boying.controller;


import com.tongji.boying.common.api.CommonPage;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.dto.orderParam.BoyingOrderReturn;
import com.tongji.boying.dto.orderParam.OrderParam;
import com.tongji.boying.model.BoyingOrder;
import com.tongji.boying.service.NumsOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@Api(tags = "NumsOrderController", description = "后台订单管理")
@RequestMapping("/order")
public class NumsOrderController {
    @Autowired
    private NumsOrderService numsOrderService;

    @ApiOperation("查看所有用户订单")
    @RequestMapping(value = "/listOrders", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<BoyingOrder>> listOrders(@Validated @RequestBody OrderParam param) {

        return CommonResult.success(CommonPage.restPage(numsOrderService.listOrders(param)));
    }

    @ApiOperation("删除某id订单")
    @RequestMapping(value = "/deleteOrder/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteOrder(@PathVariable Integer id) {
        numsOrderService.deleteOrder(id);
        return CommonResult.success("删除订单成功!");
    }

    @ApiOperation("恢复某id订单")
    @RequestMapping(value = "/recoverOrder/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult recoverOrder(@PathVariable Integer id) {
        numsOrderService.recoverOrder(id);
        return CommonResult.success("恢复订单成功!");
    }
}
