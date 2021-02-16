package com.tongji.boying.security.component;

import cn.hutool.core.util.URLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 动态权限数据源，用于获取动态权限规则
 */
public class DynamicSecurityMetadataSource implements FilterInvocationSecurityMetadataSource
{
    //后台资源规则被缓存在了一个Map对象之中
    private static Map<String, ConfigAttribute> configAttributeMap = null;

    //动态权限相关业务类，自定义的一个动态权限业务接口，其主要用于加载所有的后台资源规则。
    @Autowired
    private DynamicSecurityService dynamicSecurityService;

    @PostConstruct
    public void loadDataSource()
    {
        configAttributeMap = dynamicSecurityService.loadDataSource();
    }

    /**
     由于我们的后台资源规则被缓存在了一个Map对象之中，所以当后台资源发生变化时，我们需要清空缓存的数据，
     然后下次查询时就会被重新加载进来。
     当修改后台资源时，需要调用clearDataSource方法来清空缓存的数据。
     */
    public void clearDataSource()
    {
        configAttributeMap.clear();
        configAttributeMap = null;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException
    {
        if (configAttributeMap == null) this.loadDataSource();
        List<ConfigAttribute> configAttributes = new ArrayList<>();
        //获取当前访问的路径
        String url = ((FilterInvocation) o).getRequestUrl();
        String path = URLUtil.getPath(url);
        PathMatcher pathMatcher = new AntPathMatcher();
        Iterator<String> iterator = configAttributeMap.keySet().iterator();
        //获取访问该路径所需资源
        while (iterator.hasNext())
        {
            String pattern = iterator.next();
            if (pathMatcher.match(pattern, path))
            {
                configAttributes.add(configAttributeMap.get(pattern));
            }
        }
        // 未设置操作请求权限，返回空集合
        return configAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes()
    {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass)
    {
        return true;
    }

}
