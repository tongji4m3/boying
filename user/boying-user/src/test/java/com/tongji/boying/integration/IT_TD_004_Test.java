package com.tongji.boying.integration;

import base.InitHttpCase;
import com.tongji.boying.dto.userParam.UserRegisterParam;
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

public class IT_TD_004_Test extends InitHttpCase {
    @Autowired
    BoyingUserService boyingUserService;

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @ParameterizedTest
    @CsvFileSource(resources = "/integration/IT_TD_004_Test.csv", numLinesToSkip = 1)
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
}
