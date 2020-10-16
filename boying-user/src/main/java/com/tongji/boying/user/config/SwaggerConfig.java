package com.tongji.boying.user.config;

import com.tongji.boying.common.config.BaseSwaggerConfig;
import com.tongji.boying.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger API文档相关配置
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig
{

    @Override
    public SwaggerProperties swaggerProperties()
    {
        return SwaggerProperties.builder()
                .apiBasePackage("com.tongji.boying.user.controller")
                .title("boying-userAPI文档")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
