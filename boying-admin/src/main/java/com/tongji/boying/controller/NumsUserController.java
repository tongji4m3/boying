package com.tongji.boying.controller;


import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.model.User;
import com.tongji.boying.service.NumsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(tags = "NumsUserController", description = "后台普通用户管理")
@RequestMapping("/nums")
public class NumsUserController {
    @Autowired
    private NumsUserService numsUserService;

    @ApiOperation("删除指定普通用户信息")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Integer id)
    {
        int count = numsUserService.delete(id);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取指定id普通用户")
    @RequestMapping(value = "/getInformation/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getUserById(@PathVariable Integer id)
    {
        User user = numsUserService.getUserById(id);
        if (user!=null)
        {
            return CommonResult.success(user);
        }
        return CommonResult.failed();
    }

    @ApiOperation("切换指定id普通用户账号启用状态")
    @RequestMapping(value = "/ChangeUserStatus/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult ChangeUserStatusById(@PathVariable Integer id)
    {
        int count= numsUserService.ChangeUserStatusById(id);
        if (count>0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


}
