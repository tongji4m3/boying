package com.tongji.boying.controller;

import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.dto.adminParam.AdminInfo;
import com.tongji.boying.dto.adminParam.LoginReturn;
import com.tongji.boying.dto.adminParam.UsernameLoginParam;
import com.tongji.boying.model.BoyingAdmin;
import com.tongji.boying.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * 后台管理员登录相关,是所有管理员都能访问的板块
 */
@Controller
@Api(tags = "UmsLoginController", description = "后台管理员登录相关")
@RequestMapping("/login")
public class UmsLoginController {
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UmsAdminService adminService;

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@Validated @RequestBody UsernameLoginParam param) {
        String token = adminService.login(param);
        return CommonResult.success(new LoginReturn(token, tokenHead));
    }

    @ApiOperation(value = "获取管理员信息")
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<AdminInfo> info(Principal principal) {
        //        防止直接查询时报错
        if (principal == null) return CommonResult.unauthorized(null);
        BoyingAdmin currentAdmin = adminService.getCurrentAdmin();
        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setIcon(currentAdmin.getIcon());
        adminInfo.setLastTime(currentAdmin.getLastTime());
        adminInfo.setUsername(currentAdmin.getUsername());
        return CommonResult.success(adminInfo);
    }


}
