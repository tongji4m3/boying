package com.tongji.boying.controller;

import com.tongji.boying.common.api.CommonPage;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.dto.showParam.BoyingShowReturn;
import com.tongji.boying.dto.showParam.SmsShowListParam;
import com.tongji.boying.dto.showParam.SmsShowParam;
import com.tongji.boying.model.BoyingShow;
import com.tongji.boying.service.SmsShowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台演出管理
 */
@Controller
@Api(tags = "SmsShowController", description = "后台演出管理")
@RequestMapping("/show")
public class SmsShowController {
    @Autowired
    private SmsShowService showService;

    @ApiOperation("添加演出")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@Validated @RequestBody SmsShowParam param) {
        showService.create(param);
        return CommonResult.success("添加演出成功！");
    }

    @ApiOperation("查看演出")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<BoyingShowReturn>> list(@Validated @RequestBody SmsShowListParam param) {
        return CommonResult.success(CommonPage.restPage(showService.list(param)));
    }

    @ApiOperation("获取演出详情")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<BoyingShow> detail(@PathVariable Integer id) {
        return CommonResult.success(showService.detail(id));
    }

    @ApiOperation("修改演出")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Integer id, @Validated @RequestBody SmsShowParam param) {
        showService.update(id, param);
        return CommonResult.success("修改演出成功！");
    }
}
