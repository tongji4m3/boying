package com.tongji.boying.integration;

import base.InitHttpCase;
import com.tongji.boying.controller.BoyingOrderController;
import com.tongji.boying.dto.orderParam.UserOrderParam;
import com.tongji.boying.dto.userParam.AuthCodeLoginParam;
import com.tongji.boying.service.BoyingOrderService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class IT_TD_015_Test extends InitHttpCase {
    @Autowired
    BoyingOrderController boyingOrderController;
    @Autowired
    BoyingOrderService boyingOrderService;

    private final TestRestTemplate template = new TestRestTemplate();

    @ParameterizedTest
    @CsvFileSource(resources = "/integration/IT_TD_015_Test.csv", numLinesToSkip = 1)
    @Transactional
    public void authCodePostTest(Integer showId, Integer seatId, Integer count, String payment, String expected,String token) {
        // 请求地址
        final String url = "http://localhost:8000/order/add/";

        // 请求头设置,x-www-form-urlencoded格式的数据
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        UserOrderParam userOrderParam = new UserOrderParam();
        userOrderParam.setShowId(showId);
        userOrderParam.setSeatId(seatId);
        userOrderParam.setPayment(payment);
        userOrderParam.setCount(count);

        HttpEntity<UserOrderParam> request =
                new HttpEntity<>(userOrderParam, headers);
        System.out.println(request);

        // 发送post请求，并打印结果，以String类型接收响应结果JSON字符串
        Map result = template.postForObject(url, request, Map.class);
        System.out.println(result);
        assertEquals(expected, result.get("message"));
    }
}