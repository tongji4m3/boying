package com.tongji.boying.config;

import com.tongji.boying.model.Admin;
import com.tongji.boying.model.Resource;
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
    private final Admin admin;
    private final List<Resource> resourceList;

    public AdminUserDetails(Admin admin, List<Resource> resourceList)
    {
        this.admin = admin;
        this.resourceList = resourceList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        //返回当前用户拥有的资源权限
        return resourceList.stream()
                .map(resource -> new SimpleGrantedAuthority(resource.getResourceId() + ":" + resource.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword()
    {
        return admin.getPassword();
    }

    @Override
    public String getUsername()
    {
        return admin.getUsername();
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
        return admin.getStatus();
    }

    public Admin getAdmin()
    {
        return admin;
    }

}
