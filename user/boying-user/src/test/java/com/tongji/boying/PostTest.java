package com.tongji.boying;

import base.InitHttpCase;
import com.tongji.boying.controller.BoyingUserController; //boyingUserController类
//dto层的参数封装类
import com.tongji.boying.dto.userParam.AuthCodeLoginParam;
import com.tongji.boying.dto.userParam.TelephoneLoginParam;
import com.tongji.boying.dto.userParam.UserRegisterParam;
import com.tongji.boying.dto.userParam.UsernameLoginParam;
import com.tongji.boying.service.BoyingUserService; //boyingUserService类

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


//这里因为在InitHttpCase中配置了@SpringBootTest，所以就不写了
//@SpringBootTest
public class PostTest extends InitHttpCase
{

    @Autowired
    BoyingUserController boyingUserController;
    @Autowired
    BoyingUserService boyingUserService;

    private final TestRestTemplate template = new TestRestTemplate();
    private final UserRegisterParam registerParam = new UserRegisterParam();
    private final UsernameLoginParam nameLoginParam = new UsernameLoginParam();
    private final TelephoneLoginParam telephoneLoginParam = new TelephoneLoginParam();
    private final AuthCodeLoginParam authCodeLoginParam = new AuthCodeLoginParam();

    @ParameterizedTest
    @CsvFileSource(resources = "/registerParam.csv", numLinesToSkip = 1)
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
        System.out.println(result);
        assertEquals(Integer.parseInt(code), result.get("code"));
        assertEquals(message, result.get("message"));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/nameLoginParam.csv", numLinesToSkip = 1)
    @Transactional
    public void nameLoginPostTest(String username, String password, String expected)
    {
        // 请求地址
        final String url = "http://localhost:8000/user/usernameLogin";

        // 请求头设置,x-www-form-urlencoded格式的数据
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        nameLoginParam.setUsername(username);
        nameLoginParam.setPassword(password);

        HttpEntity<UsernameLoginParam> request =
                new HttpEntity<>(nameLoginParam, headers);

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

    @ParameterizedTest
    @CsvFileSource(resources = "/telephoneLoginParam.csv", numLinesToSkip = 1)
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

    @ParameterizedTest
    @CsvFileSource(resources = "/authCodeLoginParam.csv", numLinesToSkip = 1)
    @Transactional
    public void authCodePostTest(String telephone, String authCode, String expected)
    {
        // 请求地址
        final String url = "http://localhost:8000/user/authCodeLogin";

        // 请求头设置,x-www-form-urlencoded格式的数据
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        authCodeLoginParam.setTelephone(telephone);
        authCodeLoginParam.setAuthCode(authCode);

        HttpEntity<AuthCodeLoginParam> request =
                new HttpEntity<>(authCodeLoginParam, headers);

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