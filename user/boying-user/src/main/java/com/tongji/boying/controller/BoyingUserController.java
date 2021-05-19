package com.tongji.boying.controller;

import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.dto.userParam.*;
import com.tongji.boying.service.BoyingUserService;
import com.tongji.boying.vo.LoginVO;
import com.tongji.boying.vo.BoyingUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
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
@Api(tags = "BoyingUserController", description = "用户模块用户相关信息API")
@RequestMapping("/user")
public class BoyingUserController {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private BoyingUserService boyingUserService;

    @ApiOperation("用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> register(@Validated @RequestBody UserRegisterParam param) {
        boyingUserService.register(param);
        return CommonResult.success("注册成功");
    }

    @ApiOperation("用户账号密码登录")
    @RequestMapping(value = "/usernameLogin", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<LoginVO> usernameLogin(@Validated @RequestBody UsernameLoginParam param) {
        String token = boyingUserService.login(param);
        return CommonResult.success(new LoginVO(token, tokenHead));
    }

    @ApiOperation("用户手机号密码登录")
    @RequestMapping(value = "/telephoneLogin", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<LoginVO> telephoneLogin(@Validated @RequestBody TelephoneLoginParam param) {
        String token = boyingUserService.telephoneLogin(param);
        return CommonResult.success(new LoginVO(token, tokenHead));
    }

    @ApiOperation("用户手机号验证码登录")
    @RequestMapping(value = "/authCodeLogin", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<LoginVO> authCodeLogin(@Validated @RequestBody AuthCodeLoginParam param) {
        String token = boyingUserService.authCodeLogin(param);
        return CommonResult.success(new LoginVO(token, tokenHead));
    }

    @ApiOperation("获取用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<BoyingUserVO> info(Principal principal) {
        if (principal == null) return CommonResult.unauthorized(null);
        //将核心领域模型用户对象转化为可供UI使用的viewObject对象
        BoyingUserVO boyingUserVO = new BoyingUserVO();
        BeanUtils.copyProperties(boyingUserService.getCurrentUser(), boyingUserVO);
        return CommonResult.success(boyingUserVO);
    }

    @ApiOperation("更新个人信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> updateInfo(Principal principal, @Validated @RequestBody UpdateInfoParam param) {
        if (principal == null) return CommonResult.unauthorized(null);
        boyingUserService.updateInfo(param);
        return CommonResult.success("更新个人信息成功!");
    }


    @ApiOperation("获取验证码")
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> getAuthCode(@RequestBody String telephone) {
        boyingUserService.generateAuthCode(telephone);
        return CommonResult.success("获取验证码成功");
    }

    @ApiOperation("修改密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> updatePassword(@Validated @RequestBody UpdatePasswordParam param) {
        boyingUserService.updatePassword(param);
        return CommonResult.success(null, "密码修改成功");
    }

    @ApiOperation(value = "刷新token")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<LoginVO> refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = boyingUserService.refreshToken(token);
        if (refreshToken == null) return CommonResult.failed("token已经过期或暂时不能刷新token!");
        return CommonResult.success(new LoginVO(token, tokenHead));
    }
}
