package com.tongji.boying.controller;

import com.tongji.boying.common.api.CommonPage;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.model.ShowClass;
import com.tongji.boying.service.ShowClassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前台演出座次管理Controller
 */
@Controller
@Api(tags = "ShowClassController", description = "前台演出座次管理")
@RequestMapping("/class")
public class ShowClassController
{
    private ShowClassService classService;

    @ApiOperation("获取演出座次详情")
    @RequestMapping(value = "/detail/{classId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<ShowClass> detail(@PathVariable Integer classId)
    {
        ShowClass showClass = classService.detail(classId);
        return CommonResult.success(showClass);
    }

    @ApiOperation("获取某演唱会场次的所有座次")
    @RequestMapping(value = "/classList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<ShowClass>> getShowClassList(@RequestParam int sessionId,
                                                                @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                                @RequestParam(required = false, defaultValue = "5") Integer pageSize)
    {
        List<ShowClass> list = classService.getShowClassList(sessionId, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }
}
