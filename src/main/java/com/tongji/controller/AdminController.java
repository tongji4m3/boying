package com.tongji.controller;

import com.tongji.common.api.CommonResult;
import com.tongji.dto.AdminLoginParam;
import com.tongji.mbg.model.Admin;
import com.tongji.mbg.model.Permission;
import com.tongji.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台用户管理
 */
@Controller
@Api(tags = "AdminController", description = "后台用户管理")
@RequestMapping("/admin")
public class AdminController
{
    @Autowired
    private AdminService adminService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Admin> register(@RequestBody Admin AdminParam, BindingResult result)
    {
        Admin Admin = adminService.register(AdminParam);
        if (Admin == null)
        {
            CommonResult.failed();
        }
        return CommonResult.success(Admin);
    }

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestBody AdminLoginParam AdminLoginParam, BindingResult result)
    {
        String token = adminService.login(AdminLoginParam.getUsername(), AdminLoginParam.getPassword());
        if (token == null)
        {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation("获取用户所有权限（包括+-权限）")
    @RequestMapping(value = "/permission/{adminId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Permission>> getPermissionList(@PathVariable Integer adminId)
    {
        List<Permission> permissionList = adminService.getPermissionList(adminId);
        return CommonResult.success(permissionList);
    }
}
