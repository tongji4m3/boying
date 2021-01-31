package com.tongji.boying.controller;

import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.model.BoyingPromo;
import com.tongji.boying.model.PromoModel;
import com.tongji.boying.service.ShowPromoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 前台演出活动Controller
 */
@Controller
@Api(tags = "ShowPromoController", description = "前台演出座次相关API")
@RequestMapping("/promo")
public class ShowPromoController {
    @Autowired
    private ShowPromoService PromoService;

    @ApiOperation("获取某演唱会座次的活动信息")
    @RequestMapping(value = "/detail/{seatId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<PromoModel> getPromo(@PathVariable Integer seatId) {
        return CommonResult.success(PromoService.getPromo(seatId));
    }
}
