package com.tongji.boying.controller;

import cn.hutool.core.util.StrUtil;
import com.tongji.boying.common.api.CommonPage;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.model.BoyingSeat;
import com.tongji.boying.service.ShowSeatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 前台演出座次管理Controller
 */
@Controller
@Api(tags = "ShowSeatController", description = "前台演出座次相关API")
@RequestMapping("/seat")
public class ShowSeatController {
    @Autowired
    private ShowSeatService seatService;


    @ApiOperation("获取某演唱会场次的所有座次")
    @RequestMapping(value = "/seats", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<BoyingSeat>> getShowSeatList(@RequestBody Integer showId) {
        return CommonResult.success(seatService.getShowSeatList(showId));
    }
}
