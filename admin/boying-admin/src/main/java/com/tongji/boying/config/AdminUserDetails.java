package com.tongji.boying.config;

import com.tongji.boying.model.AdminUser;
import com.tongji.boying.model.AdminResource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SpringSecurity需要的用户详情
 */
public class AdminUserDetails implements UserDetails
{
    private final AdminUser adminUser;
    private final List<AdminResource> resourceList;

    public AdminUserDetails(AdminUser AdminUser, List<AdminResource> resourceList)
    {
        this.adminUser = AdminUser;
        this.resourceList = resourceList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        //返回当前用户拥有的资源权限
        return resourceList.stream()
                .map(AdminResource -> new SimpleGrantedAuthority(AdminResource.getId() + ":" + AdminResource.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword()
    {
        return adminUser.getPassword();
    }

    @Override
    public String getUsername()
    {
        return adminUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return adminUser.getStatus();
    }

    public AdminUser getAdmin()
    {
        return adminUser;
    }

}
