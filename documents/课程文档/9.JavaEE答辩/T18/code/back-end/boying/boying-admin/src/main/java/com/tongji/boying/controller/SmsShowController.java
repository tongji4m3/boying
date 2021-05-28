package com.tongji.boying.controller;

import cn.hutool.core.util.ObjectUtil;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.dto.SmsShowParam;
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

    @ApiOperation("查看所有演出")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult list() {
        List<BoyingShow> showList = showService.list();
        if (ObjectUtil.isEmpty(showList)) return CommonResult.failed("无角色!");
        return CommonResult.success(showList);
    }

    @ApiOperation("添加演出")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@Validated @RequestBody SmsShowParam param) {
        int count = showService.create(param);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改演出")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Integer id, @Validated @RequestBody SmsShowParam param) {
        int count = showService.update(id, param);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("要修改的演出不存在!");
    }

    @ApiOperation("删除演出")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Integer id) {
        int count = showService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("要删除的演出不存在!");
    }

    @ApiOperation("批量删除演出")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("ids") List<Integer> ids) {
        int count = showService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
