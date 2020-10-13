package com.tongji.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.tongji.mbg.mapper","com.tongji.dao"})
public class MyBatisConfig
{
}
