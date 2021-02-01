package com.tongji.boying.controller;

import com.tongji.boying.common.api.CommonPage;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.dto.showParam.ShowParam;
import com.tongji.boying.model.BoyingShow;
import com.tongji.boying.service.ShowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * 前台演出管理Controller
 */
@Controller
@Api(tags = "ShowController", description = "前台演出相关API")
@RequestMapping("/show")
public class BoyingShowController {

    @Autowired
    private ShowService showService;

    @ApiOperation("对演出的综合搜索、筛选、排序(所有字段均为可选字段)")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<BoyingShow>> search(@Validated @RequestBody ShowParam param) throws ParseException {
        return CommonResult.success(CommonPage.restPage(showService.search(param)));
    }

    @ApiOperation("获取演出详情")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<BoyingShow> detail(@PathVariable Integer id) {
        return CommonResult.success(showService.detail(id));
    }
}
