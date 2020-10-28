package com.tongji.boying.config;

import com.tongji.boying.model.Resource;
import com.tongji.boying.security.component.DynamicSecurityService;
import com.tongji.boying.security.config.SecurityConfig;
import com.tongji.boying.service.UmsAdminService;
import com.tongji.boying.service.UmsResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * mall-security模块相关配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BoyingSecurityConfig extends SecurityConfig
{

    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private UmsResourceService resourceService;

    @Bean
    public UserDetailsService userDetailsService()
    {
        //获取登录用户信息
        return username -> adminService.loadUserByUsername(username);
    }

    //    加载所有的资源 资源路径url,资源路径值(自定义为了 id:name )
    @Bean
    public DynamicSecurityService dynamicSecurityService()
    {
        return new DynamicSecurityService()
        {
            @Override
            public Map<String, ConfigAttribute> loadDataSource()
            {
                Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
                List<Resource> resourceList = resourceService.listAll();
                for (Resource resource : resourceList)
                {
                    map.put(resource.getUrl(), new org.springframework.security.access.SecurityConfig(resource.getResourceId() + ":" + resource.getName()));
                }
                return map;
            }
        };
    }
}
