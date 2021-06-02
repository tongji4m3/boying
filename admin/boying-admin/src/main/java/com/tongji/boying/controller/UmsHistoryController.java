package com.tongji.boying.controller;

import cn.hutool.core.collection.CollUtil;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.model.AdminCategory;
import com.tongji.boying.model.BoyingHistory;
import com.tongji.boying.service.UmsCategoryService;
import com.tongji.boying.service.UmsHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 后台订单历史Controller
 */
@Controller
@Api(tags = "UmsHistoryController", description = "后台订单历史Controller")
@RequestMapping("/History")
public class UmsHistoryController
{
    @Autowired
    private UmsHistoryService umsHistoryService;

    @ApiOperation("查询所有订单历史")
    @RequestMapping(value = "/listAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<BoyingHistory>> listAll()
    {
        List<BoyingHistory> histories = umsHistoryService.listAll();
        return CommonResult.success(histories);
    }
}
