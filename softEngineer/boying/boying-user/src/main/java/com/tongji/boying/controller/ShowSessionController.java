package com.tongji.boying.controller;

import cn.hutool.core.util.StrUtil;
import com.tongji.boying.common.api.CommonPage;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.model.ShowSession;
import com.tongji.boying.service.ShowSessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 前台演出场次管理Controller
 */
@Controller
@Api(tags = "ShowSessionController", description = "前台演出场次相关API")
@RequestMapping("/session")
public class ShowSessionController {
    @Autowired
    private ShowSessionService showSessionService;

    @ApiOperation("获取演出场次详情")
    @RequestMapping(value = "/detail/{sessionId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<ShowSession> detail(@PathVariable Integer sessionId) {
        ShowSession showSession = showSessionService.detail(sessionId);
        if (showSession == null) {
            Asserts.fail("找不到对应演出场次信息!");
        }
        return CommonResult.success(showSession);
    }

    @ApiOperation("获取某演唱会的所有场次")
    @RequestMapping(value = "/sessionList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<ShowSession>> getShowSessionList(@RequestBody Map<String, String> map) {
        String showIdStr = map.get("showId");
        if (StrUtil.isEmpty(showIdStr)) {
            Asserts.fail("showId不能为空");
        }
        Integer showId = null;
        Integer pageNum = null;
        Integer pageSize = null;
        try {
            pageNum = Integer.parseInt(map.getOrDefault("pageNum", "0"));
            pageSize = Integer.parseInt(map.getOrDefault("pageSize", "5"));
            showId = Integer.parseInt(showIdStr);
        }
        catch (Exception e) {
            return CommonResult.failed("输入的参数格式有误!");
        }

        List<ShowSession> list = showSessionService.getShowSessionList(showId, pageNum, pageSize);
        if (list == null || list.size() == 0) {
            Asserts.fail("找不到对应演出场次信息!");
        }
        return CommonResult.success(CommonPage.restPage(list));
    }

}
