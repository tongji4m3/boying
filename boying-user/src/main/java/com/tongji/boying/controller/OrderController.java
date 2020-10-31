package com.tongji.boying.controller;

import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.dto.FrequentParam;
import com.tongji.boying.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 订单管理Controller
 */
@Controller
@Api(tags = "OrderController", description = "订单管理")
@RequestMapping("/order")
public class OrderController
{
    @Autowired
    private OrderService orderService;

    @ApiOperation("用户下单")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(@Validated @RequestBody FrequentParam param)
    {
//        int count = orderService.add(param);
//        if (count > 0)
//        {
//            return CommonResult.success(count);
//        }
        return CommonResult.failed();
    }
}
