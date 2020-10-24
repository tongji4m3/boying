package com.tongji.boying.controller;

import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.model.Category;
import com.tongji.boying.model.Show;
import com.tongji.boying.model.ShowSession;
import com.tongji.boying.service.SessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前台演出场次管理Controller
 */
@Controller
@Api(tags = "SessionController", description = "前台演出场次管理")
@RequestMapping("/user/session")
public class SessionController
{
    private SessionService sessionService;

    @ApiOperation("获取演出场次详情")
    @RequestMapping(value = "/detail/{sessionId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<ShowSession> detail(@PathVariable Integer sessionId)
    {
        ShowSession showSession = sessionService.detail(sessionId);
        return CommonResult.success(showSession);
    }

    @ApiOperation("获取某演唱会的所以场次")
    @RequestMapping(value = "/sessionList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<ShowSession>> getShowSessionList(@RequestParam int showId)
    {
        List<ShowSession> list = sessionService.getShowSessionList(showId);
        return CommonResult.success(list);
    }

}
