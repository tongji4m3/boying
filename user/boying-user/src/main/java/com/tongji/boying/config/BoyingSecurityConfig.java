package com.tongji.boying.config;

import com.tongji.boying.security.config.SecurityConfig;
import com.tongji.boying.service.BoyingUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * boying-security模块相关配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BoyingSecurityConfig extends SecurityConfig {

    @Autowired
    private BoyingUserService boyingUserService;

    /**
     * 注解@Bean放在方法上，产生一个Bean并且交给Spring容器管理
     * 定义用户信息
     */
    @Bean
    public UserDetailsService userDetailsService() {
        //为UserDetailsService定义一个适用与boying-user组件的获取登录用户信息的方法
        return username -> boyingUserService.loadUserByUsername(username);
    }
    //不添加基于路径的动态权限控制
}
