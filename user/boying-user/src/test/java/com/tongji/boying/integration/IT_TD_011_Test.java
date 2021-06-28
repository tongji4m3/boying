package com.tongji.boying.integration;

import base.InitHttpCase;
import com.tongji.boying.controller.BoyingUserController;
import com.tongji.boying.dto.userParam.UserRegisterParam;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IT_TD_011_Test  extends InitHttpCase {
    @Autowired
    BoyingUserController boyingUserController;
    @Autowired
    BoyingUserService boyingUserService;

    private final UserRegisterParam registerParam = new UserRegisterParam();
    private final TestRestTemplate template = new TestRestTemplate();

    @ParameterizedTest
    @CsvFileSource(resources = "/integration/IT_TD_011_Test.csv", numLinesToSkip = 1)
    @Transactional
    public void registerPostTest(String username, String password, String telephone, String authCode, String icon, String code, String message)
    {
        // 请求地址
        final String url = "http://localhost:8000/user/register";
        // 请求头设置,x-www-form-urlencoded格式的数据
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        registerParam.setUsername(username);
        registerParam.setPassword(password);
        registerParam.setTelephone(telephone);
        registerParam.setAuthCode(authCode);
        registerParam.setIcon(icon);
        HttpEntity<UserRegisterParam> request = new HttpEntity<>(registerParam, headers);
        System.out.println(request);
        // 发送post请求，并打印结果，以String类型接收响应结果JSON字符串
        Map result = template.postForObject(url, request, Map.class);
        assertEquals(Integer.parseInt(code), result.get("code"));
        assertEquals(message, result.get("message"));
    }
}
