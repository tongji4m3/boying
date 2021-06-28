package com.tongji.boying.integration;

import base.InitHttpCase;
import com.tongji.boying.controller.BoyingUserController;
import com.tongji.boying.dto.userParam.TelephoneLoginParam;
import com.tongji.boying.service.BoyingUserService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class IT_TD_013_Test extends InitHttpCase {
    @Autowired
    BoyingUserController boyingUserController;
    @Autowired
    BoyingUserService boyingUserService;

    private final TelephoneLoginParam telephoneLoginParam = new TelephoneLoginParam();
    private final TestRestTemplate template = new TestRestTemplate();

    @ParameterizedTest
    @CsvFileSource(resources = "/integration/IT_TD_013_Test.csv", numLinesToSkip = 1)
    @Transactional
    public void telephoneLoginPostTest(String telephone, String password, String expected)
    {
        // 请求地址
        final String url = "http://localhost:8000/user/telephoneLogin";

        // 请求头设置,x-www-form-urlencoded格式的数据
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        telephoneLoginParam.setTelephone(telephone);
        telephoneLoginParam.setPassword(password);

        HttpEntity<TelephoneLoginParam> request =
                new HttpEntity<>(telephoneLoginParam, headers);

        System.out.println(request);

        // 发送post请求，并打印结果，以String类型接收响应结果JSON字符串
        Map result = template.postForObject(url, request, Map.class);
        System.out.println(result);

        if (Objects.equals(expected, "success"))
        {
            assertEquals(200, result.get("code"));
        } else
        { //fail
            assertNotEquals(200, result.get("code"));
        }
    }
}
