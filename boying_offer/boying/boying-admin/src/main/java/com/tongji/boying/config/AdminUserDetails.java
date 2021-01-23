package com.tongji.boying.config;

import com.tongji.boying.model.BoyingAdmin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

/**
 * SpringSecurity需要的用户详情
 */
public class AdminUserDetails implements UserDetails
{
    private final BoyingAdmin admin;

    public AdminUserDetails(BoyingAdmin admin)
    {
        this.admin = admin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        //返回当前用户的角色
        return Arrays.asList(new SimpleGrantedAuthority("TEST"));
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
//        return admin.getStatus();
        return true;
    }

    public BoyingAdmin getAdmin()
    {
        return admin;
    }

}
