package com.tongji.boying.controller;

import cn.hutool.core.util.StrUtil;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.model.User;
import com.tongji.boying.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

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
    public CommonResult register(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        String telephone = map.get("telephone");
        String authCode = map.get("authCode");

        if (StrUtil.isEmpty(username)) {
            Asserts.fail("用户名不能为空");
        }
        if (StrUtil.isEmpty(password)) {
            Asserts.fail("密码不能为空");
        }
        if (StrUtil.isEmpty(telephone)) {
            Asserts.fail("手机号不能为空");
        }
        if (StrUtil.isEmpty(authCode)) {
            Asserts.fail("验证码不能为空");
        }

        String icon = map.get("icon");
        userService.register(username, password, telephone, authCode, icon);
        return CommonResult.success(null, "注册成功");
    }

    @ApiOperation("用户账号密码登录")
    @RequestMapping(value = "/usernameLogin", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult usernameLogin(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");

        if (StrUtil.isEmpty(username)) {
            Asserts.fail("用户名不能为空");
        }
        if (StrUtil.isEmpty(password)) {
            Asserts.fail("密码不能为空");
        }

        String token = userService.login(username, password);
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation("用户手机号密码登录")
    @RequestMapping(value = "/telephoneLogin", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult telephoneLogin(@RequestBody Map<String, String> map) {
            String telephone = map.get("telephone");
        String password = map.get("password");

        if (StrUtil.isEmpty(password)) {
            Asserts.fail("密码不能为空");
        }
        if (StrUtil.isEmpty(telephone)) {
            Asserts.fail("手机号不能为空");
        }

        String token = userService.telephoneLogin(telephone, password);
        if (token == null) {
            return CommonResult.validateFailed("手机号或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation("用户手机号验证码登录")
    @RequestMapping(value = "/authCodeLogin", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "telephone", value = "手机号"),
                    @ApiImplicitParam(name = "authCode", value = "验证码")
            })
    @ResponseBody
    public CommonResult authCodeLogin(@RequestBody Map<String, String> map) {
        String telephone = map.get("telephone");
        String authCode = map.get("authCode");

        if (StrUtil.isEmpty(telephone)) {
            Asserts.fail("手机号不能为空");
        }
        if (StrUtil.isEmpty(authCode)) {
            Asserts.fail("验证码不能为空");
        }

        String token = userService.authCodeLogin(telephone, authCode);

        if (token == null) {
            return CommonResult.validateFailed("验证码错误");
        }

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation("获取用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult info(Principal principal) {
//        防止直接查询时报错
        if (principal == null) {
            return CommonResult.unauthorized(null);
        }
        User user = userService.getCurrentUser();
        return CommonResult.success(user);
    }

    @ApiOperation("更新个人信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateInfo(Principal principal, @RequestBody Map<String, String> map) {
        String realName = map.get("realName");
        String identityNumber = map.get("identityNumber");
        String email = map.get("email");
        String icon = map.getOrDefault("icon", "https://tongji4m3.oss-cn-beijing.aliyuncs.com/f_f_object_156_s512_f_object_156_0.png");
        int age = Integer.parseInt(map.getOrDefault("age", "0"));
        boolean gender = Boolean.parseBoolean(map.getOrDefault("gender", "true"));
        //        防止直接查询时报错
        if (principal == null) {
            return CommonResult.unauthorized(null);
        }
        userService.updateInfo(realName, identityNumber, email, icon, age, gender);
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
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "telephone", value = "手机号"),
                    @ApiImplicitParam(name = "password", value = "密码"),
                    @ApiImplicitParam(name = "authCode", value = "验证码")
            })
    public CommonResult updatePassword(@RequestBody Map<String, String> map) {

        String telephone = map.get("telephone");
        String password = map.get("password");
        String authCode = map.get("authCode");

        if (StrUtil.isEmpty(password)) {
            Asserts.fail("密码不能为空");
        }
        if (StrUtil.isEmpty(telephone)) {
            Asserts.fail("手机号不能为空");
        }
        if (StrUtil.isEmpty(authCode)) {
            Asserts.fail("验证码不能为空");
        }

        userService.updatePassword(telephone, password, authCode);
        return CommonResult.success(null, "密码修改成功");
    }

    @ApiOperation(value = "刷新token")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = userService.refreshToken(token);
        if (refreshToken == null) {
            return CommonResult.failed("token已经过期或暂时不能刷新token!");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }
}
