package com.tongji.boying.integration;

import base.InitHttpCase;
import com.tongji.boying.dto.userParam.UsernameLoginParam;
import com.tongji.boying.model.BoyingOrder;
import com.tongji.boying.model.BoyingSeatModel;
import com.tongji.boying.service.BoyingOrderService;
import com.tongji.boying.service.BoyingUserService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IT_TD_010_Test extends InitHttpCase {
    @Autowired
    private BoyingOrderService boyingOrderService;
    @Autowired
    private BoyingUserService boyingUserService;

    @ParameterizedTest
    @CsvFileSource(resources = "/integration/IT_TD_010_Test.csv", numLinesToSkip = 1)
    public void getShowSeat(Integer orderId, String username, String password, String expected) {
        try {
            UsernameLoginParam param = new UsernameLoginParam();
            param.setUsername(username);
            param.setPassword(password);
            boyingUserService.login(param);
            BoyingOrder boyingOrder = boyingOrderService.getItem(orderId);
            if (boyingOrder != null) {
                assertEquals(expected, String.valueOf(boyingOrder.getId()));
            }
        }catch (Exception e) {
            assertEquals(expected, e.getMessage());
        }
    }
}
