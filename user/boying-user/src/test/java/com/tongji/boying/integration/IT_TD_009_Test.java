package com.tongji.boying.integration;

import base.InitHttpCase;
import com.tongji.boying.dto.orderParam.UserOrderParam;
import com.tongji.boying.dto.userParam.AuthCodeLoginParam;
import com.tongji.boying.dto.userParam.UsernameLoginParam;
import com.tongji.boying.model.BoyingOrder;
import com.tongji.boying.service.BoyingOrderService;
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

public class IT_TD_009_Test extends InitHttpCase {
    @Autowired
    private BoyingOrderService boyingOrderService;
    @Autowired
    private BoyingUserService boyingUserService;

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @ParameterizedTest
    @CsvFileSource(resources = "/integration/IT_TD_009_Test.csv", numLinesToSkip = 1)
    public void getShowSeat(String username, String password, Integer showId, Integer seatId, Integer count, String payment, String expected) {
        UsernameLoginParam param = new UsernameLoginParam();
        param.setUsername(username);
        param.setPassword(password);
        boyingUserService.login(param);
        UserOrderParam userOrderParam = new UserOrderParam();
        userOrderParam.setShowId(showId);
        userOrderParam.setSeatId(seatId);
        userOrderParam.setPayment(payment);
        userOrderParam.setCount(count);


        Set<ConstraintViolation<UserOrderParam>> violations = validator.validate(userOrderParam);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<?> violation : violations) {
                assertEquals(expected, violation.getMessage());
            }
        } else {
            try {
                boyingOrderService.add(userOrderParam);
            } catch (Exception e) {
                assertEquals(expected, e.getMessage());
            }
        }
    }
}
