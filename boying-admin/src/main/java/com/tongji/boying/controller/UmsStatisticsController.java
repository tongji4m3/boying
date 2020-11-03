package com.tongji.boying.controller;


import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.dto.UmsRoleParam;
import com.tongji.boying.service.UmsRoleService;
import com.tongji.boying.service.UmsStatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 后台报表管理
 */
@Controller
@Api(tags = "UmsStatisticsController", description = "后台报表管理")
@RequestMapping("/statistics")
public class UmsStatisticsController
{
    @Autowired
    private UmsStatisticsService  umsStatisticsService;

    @ApiOperation("每日订单统计")
    @RequestMapping(value = "/dayOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult countOrderByDay(@RequestParam Date date)
    {
        long orderCount=umsStatisticsService.countOrderByDay(date);
        if(orderCount>0)
        {
            return CommonResult.success(orderCount);
        }
        return CommonResult.failed();
    }

    @ApiOperation("一段时间内订单统计")
    @RequestMapping(value = "/periodOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult countOrderForPeriod(@RequestParam Date dateStart,@RequestParam Date dateEnd)
    {
        long orderCount=umsStatisticsService.countOrderForPeriod(dateStart,dateEnd);
        if(orderCount>0)
        {
            return CommonResult.success(orderCount);
        }
        return CommonResult.failed();
    }

    @ApiOperation("每日销售总额")
    @RequestMapping(value = "/dayOrderMoney", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult sumOrderMoneyByDay(@RequestParam Date date)
    {
        double orderMoney=umsStatisticsService.sumOrderMoneyByDay(date);
        if(orderMoney>0)
        {
            return CommonResult.success(orderMoney);
        }
        return CommonResult.failed();
    }

    @ApiOperation("一段时间内销售总额")
    @RequestMapping(value = "/PeriodOrderMoney", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult sumOrderMoneyForPeriod(@RequestParam Date dateStart,@RequestParam Date dateEnd)
    {
        double orderMoney=umsStatisticsService.sumOrderMoneyForPeriod(dateStart,dateEnd);
        if(orderMoney>0)
        {
            return CommonResult.success(orderMoney);
        }
        return CommonResult.failed();
    }

}
