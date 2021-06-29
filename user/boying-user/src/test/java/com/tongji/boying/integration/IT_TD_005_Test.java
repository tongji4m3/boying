package com.tongji.boying.integration;

import base.InitHttpCase;
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

public class IT_TD_005_Test extends InitHttpCase {
    @Autowired
    BoyingUserService boyingUserService;

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @ParameterizedTest
    @CsvFileSource(resources = "/integration/IT_TD_005_Test.csv", numLinesToSkip = 1)
    public void nameLoginDriverTest(String username, String password, String expected) {
        UsernameLoginParam loginParam = new UsernameLoginParam();
        loginParam.setUsername(username);
        loginParam.setPassword(password);
        Set<ConstraintViolation<UsernameLoginParam>> violations = validator.validate(loginParam);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<?> violation : violations) {
                assertEquals(expected, violation.getMessage());
            }
        } else {
            try {
                boyingUserService.login(loginParam);
            } catch (Exception e) {
                assertEquals(expected, e.getMessage());
            }
        }
    }
}
