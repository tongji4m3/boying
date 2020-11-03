package com.tongji.boying.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis相关配置
 * 控制dao或者mapper文件扫描位置
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.tongji.boying.mapper", "com.tongji.boying.dao"})
public class MyBatisConfig
{
}
