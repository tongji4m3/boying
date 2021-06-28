package com.tongji.boying.integration;

import base.InitHttpCase;
import com.tongji.boying.dto.userParam.UsernameLoginParam;
import com.tongji.boying.model.BoyingSeatModel;
import com.tongji.boying.model.BoyingUser;
import com.tongji.boying.service.BoyingUserService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class IT_TD_008_Test extends InitHttpCase {
    @Autowired
    private BoyingUserService boyingUserService;

    @ParameterizedTest
    @CsvFileSource(resources = "/integration/IT_TD_008_Test.csv", numLinesToSkip = 1)
    public void getShowSeat(String username,String password,String expected) {
        try {
            UsernameLoginParam param = new UsernameLoginParam();
            param.setUsername(username);
            param.setPassword(password);
            boyingUserService.login(param);
            BoyingUser currentUser = boyingUserService.getCurrentUser();
            assertNotEquals(null,currentUser);
        } catch (Exception e) {
            assertEquals(expected, e.getMessage());
        }
    }
}
