package com.tongji.boying.controller;


import com.tongji.boying.common.api.CommonPage;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.dto.showParam.ShowSeatAddParam;
import com.tongji.boying.dto.showParam.ShowSeatListParam;
import com.tongji.boying.model.BoyingSeat;
import com.tongji.boying.service.SmsSeatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@Api(tags = "SmsSeatControl", description = "后台演出座位")
@RequestMapping("/seat")
public class SmsSeatController
{
    @Autowired
    private SmsSeatService smsSeatService;

    @ApiOperation("获取某演唱会场次的所有座次")
    @RequestMapping(value = "/seats", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<BoyingSeat>> getShowSeatList(@Validated @RequestBody ShowSeatListParam param) {
        return CommonResult.success(CommonPage.restPage(smsSeatService.getShowSeatList(param)));
    }

    @ApiOperation("添加座位")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@Validated @RequestBody ShowSeatAddParam param) {
        smsSeatService.create(param);
        return CommonResult.success("添加座位成功！");
    }
}
