package com.tongji.boying.controller;

import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.model.User;
import com.tongji.boying.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
public class UserController
{
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UserService userService;

    @ApiOperation("用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult register(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String telephone,
                                 @RequestParam String authCode)
    {
        userService.register(username, password, telephone, authCode);
        return CommonResult.success(null, "注册成功");
    }

    @ApiOperation("用户账号密码登录")
    @RequestMapping(value = "/usernameLogin", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult usernameLogin(@RequestParam String username,
                                      @RequestParam String password)
    {
        String token = userService.login(username, password);
        if (token == null)
        {
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
    public CommonResult telephoneLogin(@RequestParam String telephone,
                                       @RequestParam String password)
    {
        String token = userService.telephoneLogin(telephone, password);
        if (token == null)
        {
            return CommonResult.validateFailed("手机号或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation("用户手机号验证码登录")
    @RequestMapping(value = "/authCodeLogin", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult authCodeLogin(@RequestParam String telephone,
                                      @RequestParam String authCode)
    {
        String token = userService.authCodeLogin(telephone, authCode);
        if (token == null)
        {
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
    public CommonResult info(Principal principal)
    {
//        防止直接查询时报错
        if (principal == null)
        {
            return CommonResult.unauthorized(null);
        }
        User user = userService.getCurrentUser();
        return CommonResult.success(user);
    }

    @ApiOperation("更新个人信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateInfo(Principal principal,
                                   @RequestParam(required = false) String realName,
                                   @RequestParam(required = false) String identityNumber,
                                   @RequestParam(required = false) String email,
                                   @RequestParam(required = false, defaultValue = "https://tongji4m3.oss-cn-beijing.aliyuncs.com/f_f_object_156_s512_f_object_156_0.png") String icon,
                                   @RequestParam(required = false) int age,
                                   @RequestParam(required = false, defaultValue = "true") boolean gender
    )
    {
        //        防止直接查询时报错
        if (principal == null)
        {
            return CommonResult.unauthorized(null);
        }
        userService.updateInfo(realName, identityNumber, email, icon, age, gender);
        return CommonResult.success("更新个人信息成功!");
    }


    @ApiOperation("获取验证码")
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getAuthCode(@RequestParam String telephone)
    {
        userService.generateAuthCode(telephone);
        return CommonResult.success("获取验证码成功");
    }

    @ApiOperation("修改密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePassword(@RequestParam String telephone,
                                       @RequestParam String password,
                                       @RequestParam String authCode)
    {
        userService.updatePassword(telephone, password, authCode);
        return CommonResult.success(null, "密码修改成功");
    }

    @ApiOperation(value = "刷新token")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult refreshToken(HttpServletRequest request)
    {
        String token = request.getHeader(tokenHeader);
        String refreshToken = userService.refreshToken(token);
        if (refreshToken == null)
        {
            return CommonResult.failed("token已经过期或暂时不能刷新token!");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }
}
