package com.tongji.boying.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于配置白名单资源路径
 * 将大量的参数配置在 application.yml 文件中，
 * 通过 @ConfigurationProperties 注解，我们可以方便的获取这些参数值
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "secure.ignored")
public class IgnoreUrlsConfig
{

    private List<String> urls = new ArrayList<>();

}
