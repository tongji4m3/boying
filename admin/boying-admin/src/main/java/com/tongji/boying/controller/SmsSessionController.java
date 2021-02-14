package com.tongji.boying.controller;

import cn.hutool.core.util.ObjectUtil;
import com.tongji.boying.common.api.CommonPage;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.dto.SmsSessionParam;
import com.tongji.boying.dto.UmsRoleParam;
import com.tongji.boying.model.Menu;
import com.tongji.boying.model.ShowSession;
import com.tongji.boying.service.SmsSessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "SmsSessionControl", description = "后台演出场次")
@RequestMapping("/session")
public class SmsSessionController
{
    @Autowired
    private SmsSessionService smsSessionService;

    @ApiOperation("添加场次")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@Validated @RequestBody SmsSessionParam param)
    {
        System.out.println(param);
        int count = smsSessionService.create(param);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除场次")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Integer id)
    {
        int count = smsSessionService.delete(id);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改场次")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Integer id, @Validated @RequestBody SmsSessionParam param)
    {
        int count = smsSessionService.update(id, param);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed("要修改的演出场次不存在!");
    }

    @ApiOperation("查看某演出的所有场次")
    @RequestMapping(value = "/list/{showId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<ShowSession>> list(@PathVariable Integer showId)
    {
        List<ShowSession> showSessionList = smsSessionService.list(showId);
        if (ObjectUtil.isEmpty(showSessionList)) return CommonResult.failed("该演出无场次信息");
        return CommonResult.success(showSessionList);
    }
}
