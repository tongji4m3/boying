package com.tongji.boying.controller;

import cn.hutool.core.util.ObjectUtil;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.dto.UmsUserParam;
import com.tongji.boying.model.BoyingUser;
import com.tongji.boying.service.UmsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "UmsUserController", description = "后台用户账号管理")
@RequestMapping("/user")
public class UmsUserController
{
    @Autowired
    private UmsUserService umsUserService;

    @ApiOperation("查看所有用户")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult list()
    {
        List<BoyingUser> userList = umsUserService.list();
        if (ObjectUtil.isEmpty(userList)) return CommonResult.failed("无角色!");
        return CommonResult.success(userList);
    }

    @ApiOperation("修改用户信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Integer id, @Validated @RequestBody UmsUserParam param)
    {
        int count = umsUserService.update(id, param);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改帐号状态")
    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateStatus(@PathVariable Integer id, @RequestParam(value = "adminDelete") Boolean adminDelete)
    {

        int count = umsUserService.updateStatus(id, adminDelete);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除用户")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Integer id)
    {
        int count = umsUserService.delete(id);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        else
        {
            return CommonResult.failed("没有该用户");
        }
    }
}
