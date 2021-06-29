package com.tongji.boying.unittest.drivertest;

import base.InitHttpCase;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.controller.BoyingUserController;
import com.tongji.boying.dto.userParam.*;
import com.tongji.boying.service.BoyingUserService;
import com.tongji.boying.vo.LoginVO;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BoyingUserControllerTest extends InitHttpCase
{

    @Autowired
    @InjectMocks
    BoyingUserController boyingUserController;

    @Mock
    private BoyingUserService boyingUserService;

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();


    //register()
    @ParameterizedTest
    @CsvFileSource(resources = "/unitTest/UserController/UserControllerRegisterParam.csv", numLinesToSkip = 1)
    void registerStubTest(String username, String password, String telephone, String authCode, String icon, String code, String message)
    {

        UserRegisterParam userRegisterParam = new UserRegisterParam();
        userRegisterParam.setUsername(username);
        userRegisterParam.setPassword(password);
        userRegisterParam.setTelephone(telephone);
        userRegisterParam.setIcon(icon);
        userRegisterParam.setAuthCode(authCode);
        Set<ConstraintViolation<UserRegisterParam>> violations = validator.validate(userRegisterParam);


        if (!violations.isEmpty())
        {
            for (ConstraintViolation<?> violation : violations)
            {
                assertEquals(message, violation.getMessage());
            }
        } else
        {
            try
            {
                //注入桩类
                MockitoAnnotations.initMocks(this);
                //当controller层调用被测方法时，直接跳过service层操作，返回期望的正确值
                //由于注册成功无返回值，所以直接构建controller层需要接收的正确情况结果，并作为mock的返回值即可
                Mockito.when(boyingUserController.register(userRegisterParam)).thenReturn(CommonResult.success("注册成功"));
                System.out.println(boyingUserController);
                CommonResult<String> res = boyingUserController.register(userRegisterParam);
                System.out.println(res.getData());
                assertEquals(message, res.getData());

            } catch (Exception e)
            {
                assertEquals(message, e.getMessage());
            }
        }
    }

    //usrnameLogin()
    @ParameterizedTest
    @CsvFileSource(resources = "/unitTest/UserController/UserControllerUsernameLoginParam.csv", numLinesToSkip = 1)
    public void nameLoginStubTest(String username, String password, String expected, String token)
    {
        UsernameLoginParam loginParam = new UsernameLoginParam();
        loginParam.setUsername(username);
        loginParam.setPassword(password);
        Set<ConstraintViolation<UsernameLoginParam>> violations = validator.validate(loginParam);
        if (!violations.isEmpty())
        {
            for (ConstraintViolation<?> violation : violations)
            {
                assertEquals(expected, violation.getMessage());
            }
        } else
        {
            try
            {
                //注入桩类
                MockitoAnnotations.initMocks(this);
                //当controller层调用被测方法时，直接跳过service层操作，返回期望的正确值
                Mockito.when(boyingUserController.usernameLogin(loginParam)).thenReturn(CommonResult.success(new LoginVO(token, "登录成功")));
                CommonResult<LoginVO> res = boyingUserController.usernameLogin(loginParam);
                assertEquals(expected, res.getMessage());
                assertEquals(token, res.getData().getToken());
            } catch (Exception e)
            {
                assertEquals(expected, e.getMessage());
            }
        }
    }

    //telephoneLogin
    @ParameterizedTest
    @CsvFileSource(resources = "/unitTest/UserController/UserControllerTelephoneLoginParam.csv", numLinesToSkip = 1)
    public void telephoneLoginStubTest(String telephone, String password, String expected, String token)
    {
        TelephoneLoginParam telephoneLoginParam = new TelephoneLoginParam();
        telephoneLoginParam.setTelephone(telephone);
        telephoneLoginParam.setPassword(password);
        Set<ConstraintViolation<TelephoneLoginParam>> violations = validator.validate(telephoneLoginParam);
        if (!violations.isEmpty())
        {
            for (ConstraintViolation<?> violation : violations)
            {
                assertEquals(expected, violation.getMessage());
            }
        } else
        {
            try
            {
                //注入桩类
                MockitoAnnotations.initMocks(this);
                //当controller层调用被测方法时，直接跳过service层操作，返回期望的正确值
                Mockito.when(boyingUserController.telephoneLogin(telephoneLoginParam)).thenReturn(CommonResult.success(new LoginVO(token, "登录成功")));
                CommonResult<LoginVO> res = boyingUserController.telephoneLogin(telephoneLoginParam);
                assertEquals(expected, res.getMessage());
                assertEquals(token, res.getData().getToken());
            } catch (Exception e)
            {
                assertEquals(expected, e.getMessage());
            }
        }
    }

    //authCodeLogin
    @ParameterizedTest
    @CsvFileSource(resources = "/unitTest/UserController/UserControllerAuthCodeLoginParam.csv", numLinesToSkip = 1)
    public void authCodeStubTest(String telephone, String authCode, String expected, String token)
    {
        System.out.println("1222222");
        AuthCodeLoginParam authCodeLoginParam = new AuthCodeLoginParam();
        authCodeLoginParam.setTelephone(telephone);
        authCodeLoginParam.setAuthCode(authCode);
        System.out.println(0);
        Set<ConstraintViolation<AuthCodeLoginParam>> violations = validator.validate(authCodeLoginParam);
        System.out.println(111);

        if (!violations.isEmpty())
        {
            for (ConstraintViolation<?> violation : violations)
            {
                assertEquals(expected, violation.getMessage());
            }
        } else
        {
            try
            {
                System.out.println(123);
                MockitoAnnotations.initMocks(this);
                //当controller层调用被测方法时，直接跳过service层操作，返回期望的正确值
                Mockito.when(boyingUserController.authCodeLogin(authCodeLoginParam)).thenReturn(CommonResult.success(new LoginVO(token, "登录成功")));
                CommonResult<LoginVO> res = boyingUserController.authCodeLogin(authCodeLoginParam);
                System.out.println(res);
                assertEquals(expected, res.getMessage());
                assertEquals(token, res.getData().getToken());
            } catch (Exception e)
            {
                assertEquals(expected, e.getMessage());
            }
        }
    }
}


