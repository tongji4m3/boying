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

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> boyingUserService.loadUserByUsername(username);
    }
}
