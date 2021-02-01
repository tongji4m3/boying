package com.tongji.boying.controller;

import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.model.BoyingSeatModel;
import com.tongji.boying.service.BoyingSeatService;
import com.tongji.boying.vo.BoyingSeatVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 前台演出座次管理Controller
 */
@Controller
@Api(tags = "ShowSeatController", description = "前台演出座次相关API")
@RequestMapping("/seat")
public class BoyingSeatController {
    @Autowired
    private BoyingSeatService seatService;

    @ApiOperation("获取某演唱会场次的所有座次信息")
    @RequestMapping(value = "/seatList/{showId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<BoyingSeatVO>> getShowSeatList(@PathVariable Integer showId) {
        List<BoyingSeatModel> showSeatList = seatService.getShowSeatList(showId);
        //使用stream apiJ将list内的itemModel转化为ITEMVO;
        List<BoyingSeatVO> boyingSeatVOList = showSeatList.stream().map(this::convertVOFromModel).collect(Collectors.toList());
        return CommonResult.success(boyingSeatVOList);
    }

    @ApiOperation("获取某演唱会场次的座次信息")
    @RequestMapping(value = "/detail/{seatId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<BoyingSeatVO> getShowSeat(@PathVariable Integer seatId) {
        BoyingSeatModel boyingSeatModel = seatService.getShowSeat(seatId);
        BoyingSeatVO boyingSeatVO = convertVOFromModel(boyingSeatModel);
        return CommonResult.success(boyingSeatVO);
    }

    private BoyingSeatVO convertVOFromModel(BoyingSeatModel boyingSeatModel) {
        BoyingSeatVO boyingSeatVO = new BoyingSeatVO();
        BeanUtils.copyProperties(boyingSeatModel, boyingSeatVO);
        if (boyingSeatModel.getBoyingPromoModel() != null) {
            //有正在进行或即将进行的秒杀活动
            boyingSeatVO.setPromoStatus(boyingSeatModel.getBoyingPromoModel().getStatus());
            boyingSeatVO.setPromoId(boyingSeatModel.getBoyingPromoModel().getId());
            boyingSeatVO.setStartTime(boyingSeatModel.getBoyingPromoModel().getStartTime());
            boyingSeatVO.setPromoPrice(boyingSeatModel.getBoyingPromoModel().getPrice());
        } else {
            boyingSeatVO.setPromoStatus(0);
        }
        return boyingSeatVO;
    }


    @ApiOperation("获取某演唱会座次的库存")
    @RequestMapping(value = "/seatStock/{seatId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> getSeatStock(@PathVariable Integer seatId) {
        return CommonResult.success(seatService.getSeatStock(seatId));
    }
}
