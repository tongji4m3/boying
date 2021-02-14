package com.tongji.boying.controller;

import cn.hutool.core.collection.CollUtil;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.UmsAdminInfoParam;
import com.tongji.boying.model.Admin;
import com.tongji.boying.model.Menu;
import com.tongji.boying.service.UmsAdminService;
import com.tongji.boying.service.UmsMenuService;
import com.tongji.boying.service.UmsResourceService;
import com.tongji.boying.service.UmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台管理员登录相关,是所有管理员都能访问的板块
 */
@Controller
@Api(tags = "UmsLoginController", description = "后台管理员登录相关")
@RequestMapping("/login")
public class UmsLoginController
{

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private UmsRoleService roleService;
    @Autowired
    private UmsResourceService resourceService;

    @Autowired
    private UmsMenuService menuService;

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestParam String username,
                              @RequestParam String password)
    {
        String token = adminService.login(username, password);
        if (token == null)
        {
            return CommonResult.failed("管理员名称或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "刷新token")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult refreshToken(HttpServletRequest request)
    {
        String token = request.getHeader(tokenHeader);
        String refreshToken = adminService.refreshToken(token);
        if (refreshToken == null)
        {
            return CommonResult.failed("token已经过期！");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "获取当前登录管理员信息")
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getAdminInfo(Principal principal)
    {
        //        防止没有登录直接查询时报错
        if (principal == null)
        {
            return CommonResult.unauthorized(null);
        }
        Admin admin = adminService.getCurrentAdmin();
        Map<String, Object> data = new HashMap<>();
        data.put("username", admin.getUsername());
        data.put("icon", admin.getIcon());
        data.put("email", admin.getEmail());
        data.put("loginTime", admin.getLoginTime());
        data.put("menus", menuService.categoryMap(admin.getAdminId()));
        data.put("resource", roleService.getResourceList(admin.getAdminId()));
        data.put("roles", adminService.getRoleList(admin.getAdminId()));
        return CommonResult.success(data);
    }

    @ApiOperation(value = "获取当前登录管理员的菜单信息,用于前端动态生成菜单")
    @RequestMapping(value = "/menu", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getAdminMenu(Principal principal)
    {
        //        防止没有登录直接查询时报错
        if (principal == null)
        {
            return CommonResult.unauthorized(null);
        }
        Admin currentAdmin = adminService.getCurrentAdmin();
        Map<Menu, List<Menu>> data = menuService.categoryMap(currentAdmin.getAdminId());
        if (CollUtil.isEmpty(data))
        {
            Asserts.fail("当前登录管理员的菜单为空!");
        }
        return CommonResult.success(data);
    }

    @ApiOperation("修改密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePassword(@RequestParam String username,
                                       @RequestParam String password,
                                       @RequestParam String newPassword)
    {
        int status = adminService.updatePassword(username, password, newPassword);
        if (status > 0)
        {
            return CommonResult.success(status);
        } else if (status == -1)
        {
            return CommonResult.failed("提交参数不合法");
        } else if (status == -2)
        {
            return CommonResult.failed("找不到该管理员");
        } else if (status == -3)
        {
            return CommonResult.failed("旧密码错误");
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改自己的信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(Principal principal,
                               @RequestParam(required = false) String icon,
                               @RequestParam(required = false) String email)
    {
        UmsAdminInfoParam param = new UmsAdminInfoParam();
        param.setIcon(icon);
        param.setEmail(email);
        //        防止没有登录直接查询时报错
        if (principal == null)
        {
            return CommonResult.unauthorized(null);
        }
        Admin admin = adminService.getCurrentAdmin();
        int count = adminService.update(admin.getAdminId(), param);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
