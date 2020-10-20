package com.tongji.boying.security.component;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * 动态权限相关业务类
 *自定义的一个动态权限业务接口，其主要用于加载所有的后台资源规则。
 */
public interface DynamicSecurityService
{
    /**
     * 加载资源ANT通配符和资源对应MAP
     */
    Map<String, ConfigAttribute> loadDataSource();
}
