package com.tongji.boying.controller;

import com.tongji.boying.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
@Api(tags = "UserController", description = "用户信息管理")
public class UserController
{

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "test", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("获取用户信息")
    public String test()
    {
        adminService.getAdmin(50);
        return ".toString()";
    }
}
