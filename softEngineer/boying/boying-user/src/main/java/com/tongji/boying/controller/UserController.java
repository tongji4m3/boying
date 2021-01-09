package com.tongji.boying.controller;

import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.dto.userParam.*;
import com.tongji.boying.service.UserService;
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

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * 用户登录注册等相关信息管理Controller
 */
@Controller
@Api(tags = "UserController", description = "用户模块用户相关信息API")
@RequestMapping("/user")
public class UserController {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UserService userService;

    @ApiOperation("用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult register(@Validated @RequestBody UserRegisterParam param) {
        userService.register(param);
        return CommonResult.success(null, "注册成功");
    }

    @ApiOperation("用户账号密码登录")
    @RequestMapping(value = "/usernameLogin", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult usernameLogin(@Validated @RequestBody UsernameLoginParam param) {
        String token = userService.login(param);
        return CommonResult.success(new LoginReturn(token, tokenHead));
    }

    @ApiOperation("用户手机号密码登录")
    @RequestMapping(value = "/telephoneLogin", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult telephoneLogin(@Validated @RequestBody TelephoneLoginParam param) {
        String token = userService.telephoneLogin(param);
        return CommonResult.success(new LoginReturn(token, tokenHead));
    }

    @ApiOperation("用户手机号验证码登录")
    @RequestMapping(value = "/authCodeLogin", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult authCodeLogin(@Validated @RequestBody AuthCodeLoginParam param) {
        String token = userService.authCodeLogin(param);
        return CommonResult.success(new LoginReturn(token, tokenHead));
    }

    @ApiOperation("获取用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult info(Principal principal) {
//        防止直接查询时报错
        if (principal == null) return CommonResult.unauthorized(null);
        return CommonResult.success(userService.getCurrentUser());
    }

    @ApiOperation("更新个人信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateInfo(Principal principal, @Validated @RequestBody UpdateInfoParam param) {
        //        防止直接查询时报错
        if (principal == null) return CommonResult.unauthorized(null);
        userService.updateInfo(param);
        return CommonResult.success("更新个人信息成功!");
    }


    @ApiOperation("获取验证码")
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getAuthCode(@RequestBody String telephone) {
        userService.generateAuthCode(telephone);
        return CommonResult.success("获取验证码成功");
    }

    @ApiOperation("修改密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePassword(@Validated @RequestBody UpdatePasswordParam param) {
        userService.updatePassword(param);
        return CommonResult.success(null, "密码修改成功");
    }

    @ApiOperation(value = "刷新token")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = userService.refreshToken(token);
        if (refreshToken == null) return CommonResult.failed("token已经过期或暂时不能刷新token!");
        return CommonResult.success(new LoginReturn(token, tokenHead));
    }
}
