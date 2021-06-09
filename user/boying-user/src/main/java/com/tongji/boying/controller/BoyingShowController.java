package com.tongji.boying.controller;

import com.tongji.boying.common.api.CommonPage;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.dto.showParam.ShowParam;
import com.tongji.boying.model.BoyingSeatModel;
import com.tongji.boying.model.BoyingShow;
import com.tongji.boying.service.BoyingSeatService;
import com.tongji.boying.service.BoyingShowService;
import com.tongji.boying.vo.BoyingSeatVO;
import com.tongji.boying.vo.BoyingShowListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 前台演出管理Controller
 */
@Controller
@Api(tags = "BoyingShowController", description = "前台演出相关API")
@RequestMapping("/show")
public class BoyingShowController {
    @Autowired
    private BoyingShowService boyingShowService;
    @Autowired
    private BoyingSeatService boyingSeatService;

    @ApiOperation("对演出的综合搜索、筛选、排序(所有字段均为可选字段)")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<BoyingShowListVO>> search(@Validated @RequestBody ShowParam param) {
        List<BoyingShow> boyingShowList = boyingShowService.search(param);
        return CommonResult.success(CommonPage.restPage(boyingShowList.stream().map(this::convertVOFromModel).collect(Collectors.toList())));
    }

    @ApiOperation("获取演出详情")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<BoyingShowListVO> detail(@PathVariable Integer id) {
        return CommonResult.success(convertVOFromModel(boyingShowService.detail(id)));
    }

    private BoyingShowListVO convertVOFromModel(BoyingShow boyingShow) {
        BoyingShowListVO boyingShowListVO = new BoyingShowListVO();
        BeanUtils.copyProperties(boyingShow, boyingShowListVO);
        List<BoyingSeatModel> showSeatList = boyingSeatService.getShowSeatList(boyingShow.getId());

        Integer totalCapacity = 0, totalStock = 0;
        for (BoyingSeatModel boyingSeatModel : showSeatList) {
            totalCapacity += boyingSeatModel.getCapacity();
            totalStock += boyingSeatModel.getStock();
        }
        boyingShowListVO.setTotalCapacity(totalCapacity);
        boyingShowListVO.setTotalStock(totalStock);
        return boyingShowListVO;
    }
}
