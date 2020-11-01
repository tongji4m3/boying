package com.tongji.boying.controller;

import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.dto.UmsRoleParam;
import com.tongji.boying.dto.UmsShowParam;
import com.tongji.boying.service.UmsRoleService;
import com.tongji.boying.service.UmsShowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 后台演出管理
 */
@Controller
@Api(tags = "UmsShowController", description = "后台演出管理")
@RequestMapping("/show")
public class UmsShowController
{
    @Autowired
    private UmsShowService  showService;

    @ApiOperation("添加演出")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@Validated @RequestBody UmsShowParam param)
    {
        int count = showService.create(param);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
