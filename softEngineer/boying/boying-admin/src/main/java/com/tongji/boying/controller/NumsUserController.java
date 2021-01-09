package com.tongji.boying.controller;


import cn.hutool.core.util.ObjectUtil;
import com.tongji.boying.common.api.CommonPage;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.dto.NumsUserParam;
import com.tongji.boying.dto.userParam.GetUserByNameParam;
import com.tongji.boying.model.BoyingShow;
import com.tongji.boying.model.BoyingUser;
import com.tongji.boying.model.BoyingOrder;
import com.tongji.boying.service.NumsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "NumsUserController", description = "后台普通用户管理")
@RequestMapping("/user")
public class NumsUserController {
    @Autowired
    private NumsUserService numsUserService;

    @ApiOperation("添加新用户")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(@Validated @RequestBody NumsUserParam param) {
        numsUserService.add(param);
        return CommonResult.success("添加新用户成功！");
    }

    @ApiOperation("获取指定id普通用户")
    @RequestMapping(value = "/getInformation/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getUserById(@PathVariable Integer id) {
        BoyingUser user = numsUserService.getUserById(id);
        return CommonResult.success(user);
    }

    @ApiOperation("根据用户名模糊匹配获取普通用户")
    @RequestMapping(value = "/getUserByName", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<BoyingUser>> getUserByName(@Validated @RequestBody GetUserByNameParam param) {
        return CommonResult.success(CommonPage.restPage(numsUserService.getUserByName(param)));
    }

    @ApiOperation("查看所有用户")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult list() {
        return CommonResult.success(numsUserService.listAllUsers());
    }

    @ApiOperation("切换指定id普通用户账号启用状态")
    @RequestMapping(value = "/ChangeUserStatus/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult ChangeUserStatusById(@PathVariable Integer id) {
        numsUserService.ChangeUserStatusById(id);
        return CommonResult.success("切换用户状态成功！");
    }


    @ApiOperation("查看所有用户订单")
    @RequestMapping(value = "/listOrders", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult listOrders() {
        List<BoyingOrder> ordersList = numsUserService.listOrders();
        if (ObjectUtil.isEmpty(ordersList)) return CommonResult.failed("不存在任何订单");
        return CommonResult.success(ordersList);
    }

    @ApiOperation("删除某id订单")
    @RequestMapping(value = "/deleteOrder/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteOrders(@PathVariable Integer id) {
        int count = numsUserService.deleteOrder(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("要删除的订单不存在!");
    }
}
