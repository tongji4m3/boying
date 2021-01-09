package com.tongji.boying.controller;


import cn.hutool.core.util.ObjectUtil;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.dto.SmsSessionParam;
import com.tongji.boying.model.BoyingSeat;
import com.tongji.boying.service.SmsSeatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "SmsSeatControl", description = "后台演出座位")
@RequestMapping("/seat")
public class SmsSeatController
{
    @Autowired
    private SmsSeatService smsSeatService;

    @ApiOperation("添加座位")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@Validated @RequestBody SmsSessionParam param) {
        System.out.println(param);
        int count = smsSeatService.create(param);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除座位")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Integer id) {
        int count = smsSeatService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改座位")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Integer id, @Validated @RequestBody SmsSessionParam param) {
        int count = smsSeatService.update(id, param);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("要修改的演出座位不存在!");
    }

    @ApiOperation("查看某演出的所有座位")
    @RequestMapping(value = "/list/{showId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<BoyingSeat>> list(@PathVariable Integer showId) {
        List<BoyingSeat> seatList = smsSeatService.list(showId);
        if (ObjectUtil.isEmpty(seatList)) return CommonResult.failed("该演出无座位信息");
        return CommonResult.success(seatList);
    }
}
