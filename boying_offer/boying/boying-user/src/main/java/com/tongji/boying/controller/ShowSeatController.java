package com.tongji.boying.controller;

import com.tongji.boying.common.api.CommonPage;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.model.BoyingSeat;
import com.tongji.boying.model.ItemModel;
import com.tongji.boying.service.ShowSeatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前台演出座次管理Controller
 */
@Controller
@Api(tags = "ShowSeatController", description = "前台演出座次相关API")
@RequestMapping("/seat")
public class ShowSeatController {
    @Autowired
    private ShowSeatService seatService;

    @ApiOperation("获取某演唱会场次的所有座次信息")
    @RequestMapping(value = "/detail/{showId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<ItemModel>> getShowSeatList(@PathVariable Integer showId) {
        return CommonResult.success(seatService.getShowSeatList(showId));
    }


    @ApiOperation("获取某演唱会座次的库存")
    @RequestMapping(value = "/seatStock/{seatId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> getSeatStock(@PathVariable Integer seatId) {
        return CommonResult.success(seatService.getSeatStock(seatId));
    }
}
