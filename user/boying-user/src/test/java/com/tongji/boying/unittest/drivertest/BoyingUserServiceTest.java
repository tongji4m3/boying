package com.tongji.boying.unittest.drivertest;

import base.InitHttpCase;
import com.tongji.boying.dto.userParam.AuthCodeLoginParam;
import com.tongji.boying.dto.userParam.TelephoneLoginParam;
import com.tongji.boying.dto.userParam.UserRegisterParam;
import com.tongji.boying.dto.userParam.UsernameLoginParam;
import com.tongji.boying.service.BoyingUserService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoyingUserServiceTest extends InitHttpCase
{
    @Autowired
    BoyingUserService boyingUserService;

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    //register()方法测试
    @ParameterizedTest
    @CsvFileSource(resources = "/unitTest/UserService/UserServiceRegisterParam.csv", numLinesToSkip = 1)
    void registerDriverTest(String username, String password, String telephone, String authCode, String icon, String code, String message)
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
            boyingUserService.generateAuthCode(telephone);
            try
            {
                boyingUserService.register(userRegisterParam);
            } catch (Exception e)
            {
                assertEquals(message, e.getMessage());
            }
        }
    }

    //login()方法测试
    @ParameterizedTest
    @CsvFileSource(resources = "/unitTest/UserService/UserServiceLoginParam.csv", numLinesToSkip = 1)
    public void nameLoginDriverTest(String username, String password, String expected)
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
                boyingUserService.login(loginParam);
            } catch (Exception e)
            {
                assertEquals(expected, e.getMessage());
            }
        }
    }

    //teleephoneLogin()方法测试
    @ParameterizedTest
    @CsvFileSource(resources = "/unitTest/UserService/UserServiceTelephoneLoginParam.csv", numLinesToSkip = 1)
    public void telephoneLoginDriverTest(String telephone, String password, String expected)
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
                boyingUserService.telephoneLogin(telephoneLoginParam);
            } catch (Exception e)
            {
                assertEquals(expected, e.getMessage());
            }
        }
    }

    //authCodeLogin()方法测试
    @ParameterizedTest
    @CsvFileSource(resources = "/unitTest/UserService/UserServiceauthCodeLoginParam.csv", numLinesToSkip = 1)
    public void authCodeDriverTest(String telephone, String authCode, String expected)
    {
        AuthCodeLoginParam authCodeLoginParam = new AuthCodeLoginParam();
        authCodeLoginParam.setTelephone(telephone);
        authCodeLoginParam.setAuthCode(authCode);
        Set<ConstraintViolation<AuthCodeLoginParam>> violations = validator.validate(authCodeLoginParam);
        if (!violations.isEmpty())
        {
            for (ConstraintViolation<?> violation : violations)
            {
                assertEquals(expected, violation.getMessage());
            }
        } else
        {
            boyingUserService.generateAuthCode(telephone);
            try
            {
                boyingUserService.authCodeLogin(authCodeLoginParam);
            } catch (Exception e)
            {
                assertEquals(expected, e.getMessage());
            }
        }
    }
}
