package com.tongji.boying.controller;


import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.service.UmsStatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 后台报表管理
 */
@Controller
@Api(tags = "UmsStatisticsController", description = "后台报表管理")
@RequestMapping("/statistics")
public class UmsStatisticsController {
    @Autowired
    private UmsStatisticsService umsStatisticsService;

    @ApiOperation("每日订单统计")
    @RequestMapping(value = "/dayOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult countOrderByDay(@RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        long orderCount = -1;
        orderCount = umsStatisticsService.countOrderByDay(date);
        if (orderCount > -1) {
            return CommonResult.success(orderCount);
        }
        return CommonResult.failed();
    }

    @ApiOperation("一段时间内订单统计")
    @RequestMapping(value = "/periodOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult countOrderForPeriod(@RequestBody Map<String, Date> map) {
        Date dateStart = map.get("dateStart");
        Date dateEnd = map.get("dateEnd");
        if (dateStart.getTime() > dateEnd.getTime()) {
            return CommonResult.failed("时间顺序出错!");
        }
        Map<LocalDate, Long> result = new HashMap<>();
        Instant startInstant = dateStart.toInstant();
        Instant endInstant = dateEnd.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate startLocalDate = startInstant.atZone(zoneId).toLocalDate();
        LocalDate endLocalDate = endInstant.atZone(zoneId).toLocalDate();
        for (; startLocalDate.isBefore(endLocalDate); startLocalDate = startLocalDate.plusDays(1)) {
            ZonedDateTime zdt = startLocalDate.atStartOfDay(zoneId);
            long sum = umsStatisticsService.countOrderByDay(Date.from(zdt.toInstant()));
            result.put(startLocalDate, sum);
        }

        if (result.size() > 0) {
            return CommonResult.success(result);
        }
        return CommonResult.failed();
    }

    @ApiOperation("每日销售总额")
    @RequestMapping(value = "/dayOrderMoney", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult sumOrderMoneyByDay(@RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        double orderMoney = -1;
        orderMoney = umsStatisticsService.sumOrderMoneyByDay(date);
        if (orderMoney > -1) {
            return CommonResult.success(orderMoney);
        }
        return CommonResult.failed();
    }

    @ApiOperation("一段时间内销售总额")
    @RequestMapping(value = "/PeriodOrderMoney", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult sumOrderMoneyForPeriod(@RequestBody Map<String, Date> map) {
        System.out.println(map);
        Date dateStart = map.get("dateStart");
        Date dateEnd = map.get("dateEnd");
        System.out.println(dateStart);
        System.out.println(dateEnd);
        System.out.println("hellohellohello");
        if (dateStart.getTime() > dateEnd.getTime()) {
            return CommonResult.failed("时间顺序出错!");
        }
        Map<LocalDate, Double> result = new HashMap<>();
        Instant startInstant = dateStart.toInstant();
        Instant endInstant = dateEnd.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate startLocalDate = startInstant.atZone(zoneId).toLocalDate();
        LocalDate endLocalDate = endInstant.atZone(zoneId).toLocalDate();
        for (; startLocalDate.isBefore(endLocalDate); startLocalDate = startLocalDate.plusDays(1)) {
            ZonedDateTime zdt = startLocalDate.atStartOfDay(zoneId);
            double sum = umsStatisticsService.sumOrderMoneyByDay(Date.from(zdt.toInstant()));
            result.put(startLocalDate, sum);
        }

        if (result.size() > 0) {
            return CommonResult.success(result);
        }
        return CommonResult.failed();
    }

    @ApiOperation("平台总销售额统计")
    @RequestMapping(value = "/AllOrderMoney", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult sumAllOrderMoney() {
        double orderMoney = -1;
        orderMoney = umsStatisticsService.sumAllOrderMoney();
        if (orderMoney > -1) {
            return CommonResult.success(orderMoney);
        }
        return CommonResult.failed();
    }

    @ApiOperation("每日新增用户统计")
    @RequestMapping(value = "/userDailyGrowth", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult countUserDailyGrowth(@RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        long userDailyGrowth = -1;
        userDailyGrowth = umsStatisticsService.countUserDailyGrowth(date);
        if (userDailyGrowth > -1) {
            return CommonResult.success(userDailyGrowth);
        }
        return CommonResult.failed();
    }

    @ApiOperation("一段时间新增用户统计")
    @RequestMapping(value = "/userPeriodGrowth", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult countUserGrowthForPeriod(@RequestBody Map<String, Date> map) {
        Date dateStart = map.get("dateStart");
        Date dateEnd = map.get("dateEnd");
        if (dateStart.getTime() > dateEnd.getTime()) {
            return CommonResult.failed("时间顺序出错!");
        }
        Map<LocalDate, Long> result = new HashMap<>();
        Instant startInstant = dateStart.toInstant();
        Instant endInstant = dateEnd.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate startLocalDate = startInstant.atZone(zoneId).toLocalDate();
        LocalDate endLocalDate = endInstant.atZone(zoneId).toLocalDate();
        for (; startLocalDate.isBefore(endLocalDate); startLocalDate = startLocalDate.plusDays(1)) {
            ZonedDateTime zdt = startLocalDate.atStartOfDay(zoneId);
            long sum = umsStatisticsService.countUserDailyGrowth(Date.from(zdt.toInstant()));
            result.put(startLocalDate, sum);
        }

        if (result.size() > 0) {
            return CommonResult.success(result);
        }
        return CommonResult.failed();

    }

}
