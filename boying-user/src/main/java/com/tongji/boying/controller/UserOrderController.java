package com.tongji.boying.controller;

import com.tongji.boying.common.api.CommonPage;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.dto.UserOrderParam;
import com.tongji.boying.model.UserOrder;
import com.tongji.boying.service.UserOrderService;
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
public class UserOrderController
{
    @Autowired
    private UserOrderService orderService;

    @ApiOperation("用户下单")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(@Validated @RequestBody UserOrderParam param)
    {
        int count = orderService.add(param);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除订单")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable int id)
    {
        int count = orderService.delete(id);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("显示所有订单")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<UserOrder>> list(@RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                    @RequestParam(required = false, defaultValue = "5") Integer pageSize)
    {
        List<UserOrder> orderList = orderService.list(pageNum, pageSize);
        if (orderList.size() == 0) return CommonResult.failed("当前用户无订单!");
        return CommonResult.success(CommonPage.restPage(orderList));
    }

    @ApiOperation("获取订单详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UserOrder> getItem(@PathVariable int id)
    {
        UserOrder order = orderService.getItem(id);
        if (order == null) return CommonResult.failed("当前用户无此订单!");
        return CommonResult.success(order);
    }
}
