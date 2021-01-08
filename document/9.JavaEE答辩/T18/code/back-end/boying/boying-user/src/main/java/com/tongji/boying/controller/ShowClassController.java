package com.tongji.boying.controller;

import cn.hutool.core.util.StrUtil;
import com.tongji.boying.common.api.CommonPage;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.model.ShowClass;
import com.tongji.boying.service.ShowClassService;
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
@Api(tags = "ShowClassController", description = "前台演出座次相关API")
@RequestMapping("/class")
public class ShowClassController {
    @Autowired
    private ShowClassService classService;

    @ApiOperation("获取演出座次详情")
    @RequestMapping(value = "/detail/{classId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<ShowClass> detail(@PathVariable Integer classId) {
        ShowClass showClass = classService.detail(classId);
        if (showClass == null) {
            Asserts.fail("找不到对应演出座次信息!");
        }
        return CommonResult.success(showClass);
    }

    @ApiOperation("获取某演唱会场次的所有座次")
    @RequestMapping(value = "/classList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<ShowClass>> getShowClassList(@RequestBody Map<String, String> map) {
        String sessionIdStr = map.get("sessionId");
        if (StrUtil.isEmpty(sessionIdStr)) {
            Asserts.fail("sessionId不能为空");
        }
        Integer sessionId = null;
        Integer pageNum = null;
        Integer pageSize = null;
        try {
            pageNum = Integer.parseInt(map.getOrDefault("pageNum", "0"));
            pageSize = Integer.parseInt(map.getOrDefault("pageSize", "5"));
            sessionId = Integer.parseInt(sessionIdStr);
        }
        catch (Exception e) {
            return CommonResult.failed("输入的参数格式有误!");
        }


        List<ShowClass> list = classService.getShowClassList(sessionId, pageNum, pageSize);
        if (list == null || list.size() == 0) {
            Asserts.fail("找不到对应演出座次信息!");
        }
        return CommonResult.success(CommonPage.restPage(list));
    }
}
