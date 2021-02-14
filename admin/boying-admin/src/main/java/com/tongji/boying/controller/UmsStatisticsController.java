package com.tongji.boying.controller;


import com.tongji.boying.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
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
    public CommonResult countOrderByDay(@RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd")Date date)
    {
        long orderCount=-1;
        orderCount=umsStatisticsService.countOrderByDay(date);
        if(orderCount>-1)
        {
            return CommonResult.success(orderCount);
        }
        return CommonResult.failed();
    }

    @ApiOperation("一段时间内订单统计")
    @RequestMapping(value = "/periodOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult countOrderForPeriod(@RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateStart,@RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd")Date dateEnd)
    {
        if(dateStart.getTime()>dateEnd.getTime())
        {
            return CommonResult.failed("时间顺序出错!");
        }
        long orderCount=-1;
        orderCount=umsStatisticsService.countOrderForPeriod(dateStart,dateEnd);
        if(orderCount>-1)
        {
            return CommonResult.success(orderCount);
        }
        return CommonResult.failed();
    }

    @ApiOperation("每日销售总额")
    @RequestMapping(value = "/dayOrderMoney", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult sumOrderMoneyByDay(@RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd")Date date)
    {
        double orderMoney=-1;
        orderMoney=umsStatisticsService.sumOrderMoneyByDay(date);
        if(orderMoney>-1)
        {
            return CommonResult.success(orderMoney);
        }
        return CommonResult.failed();
    }

    @ApiOperation("一段时间内销售总额")
    @RequestMapping(value = "/PeriodOrderMoney", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult sumOrderMoneyForPeriod(@RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateStart,@RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateEnd)
    {
        if(dateStart.getTime()>dateEnd.getTime())
        {
            return CommonResult.failed("时间顺序出错!");
        }
        double orderMoney=-1;
        orderMoney=umsStatisticsService.sumOrderMoneyForPeriod(dateStart,dateEnd);
        if(orderMoney>-1)
        {
            return CommonResult.success(orderMoney);
        }
        return CommonResult.failed();
    }

    @ApiOperation("平台总销售额统计")
    @RequestMapping(value = "/AllOrderMoney", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult sumAllOrderMoney()
    {
        double orderMoney=-1;
        orderMoney=umsStatisticsService.sumAllOrderMoney();
        if(orderMoney>-1)
        {
            return CommonResult.success(orderMoney);
        }
        return CommonResult.failed();
    }

    @ApiOperation("每日新增用户统计")
    @RequestMapping(value = "/userDailyGrowth", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult countUserDailyGrowth(@RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd") Date date)
    {
        long userDailyGrowth=-1;
        userDailyGrowth=umsStatisticsService.countUserDailyGrowth(date);
        if(userDailyGrowth>-1)
        {
            return CommonResult.success(userDailyGrowth);
        }
        return CommonResult.failed();
    }

    @ApiOperation("一段时间新增用户统计")
    @RequestMapping(value = "/userPeriodGrowth", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult countUserGrowthForPeriod(@RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd")Date dateStart,@RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateEnd)
    {
        if(dateStart.getTime()>dateEnd.getTime())
        {
            return CommonResult.failed("时间顺序出错!");
        }
        long userDailyGrowth=-1;
        userDailyGrowth=umsStatisticsService.countUserGrowthForPeriod(dateStart,dateEnd);
        if(userDailyGrowth>-1)
        {
            return CommonResult.success(userDailyGrowth);
        }
        return CommonResult.failed();
    }

}
