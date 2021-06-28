package com.tongji.boying;

import base.InitHttpCase;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.controller.BoyingUserController;
import com.tongji.boying.dto.userParam.AuthCodeLoginParam;
import com.tongji.boying.dto.userParam.TelephoneLoginParam;
import com.tongji.boying.dto.userParam.UserRegisterParam;
import com.tongji.boying.dto.userParam.UsernameLoginParam;
import com.tongji.boying.vo.LoginVO;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StubTest extends InitHttpCase
{

    BoyingUserController boyingUserController = new BoyingUserController();

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @ParameterizedTest
    @CsvFileSource(resources = "/registerStubParam.csv", numLinesToSkip = 1)
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
                System.out.println(boyingUserController.toString());
                CommonResult<String> res = boyingUserController.register(userRegisterParam);
                assertEquals(message, res.getData());
            } catch (Exception e)
            {
                assertEquals(message, e.getMessage());
            }
        }
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/nameLoginStubParam.csv", numLinesToSkip = 1)
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
                CommonResult<LoginVO> res = boyingUserController.usernameLogin(loginParam);
                assertEquals(expected, res.getMessage());
                assertEquals(token, res.getData().getToken());
            } catch (Exception e)
            {
                assertEquals(expected, e.getMessage());
            }
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/telephoneLoginStubParam.csv", numLinesToSkip = 1)
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
                CommonResult<LoginVO> res = boyingUserController.telephoneLogin(telephoneLoginParam);
                assertEquals(expected, res.getMessage());
                assertEquals(token, res.getData().getToken());
            } catch (Exception e)
            {
                assertEquals(expected, e.getMessage());
            }
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/authCodeLoginStubParam.csv", numLinesToSkip = 1)
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
