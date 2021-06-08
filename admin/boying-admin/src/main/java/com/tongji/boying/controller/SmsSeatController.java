package com.tongji.boying.controller;

import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.dto.SmsSeatParam;
import com.tongji.boying.model.BoyingSeat;
import com.tongji.boying.model.BoyingShow;
import com.tongji.boying.service.SmsSeatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@Api(tags = "SmsSeatController", description = "后台座次管理")
@RequestMapping("/seat")
public class SmsSeatController {
    @Autowired
    private SmsSeatService seatService;

    @ApiOperation("添加座次")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@Validated @RequestBody SmsSeatParam param)
    {
        System.out.println(param);
        int count = seatService.create(param);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改座次")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Integer id,@Validated @RequestBody SmsSeatParam param)
    {
        int count = seatService.update(id,param);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed("要修改的座次不存在");
    }

    @ApiOperation("删除座次")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Integer id)
    {
        int count = seatService.delete(id);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed("要删除的座次不存在");
    }

    @ApiOperation("显示座次")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult list()
    {
        List<BoyingSeat> list = seatService.list();
        if (list !=null)
        {
            return CommonResult.success("座次显示成功");
        }
        return CommonResult.failed("数据库中暂无座次");
    }

    @ApiOperation("获取某个show的所有座次")
    @RequestMapping(value = "/getShowSeat/{showId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getShowSeat(@PathVariable Integer showId)
    {
        List<BoyingSeat> boyingSeat = seatService.getShowSeat(showId);
        return CommonResult.success(boyingSeat);
    }
}
