//实现整个项目安全管理的内容，链接boying-security，security涉及到
package com.tongji.boying.bo;

import com.tongji.boying.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

/**
 * SpringSecurity需要的用户详情
 */
public class BoyingUserDetails implements UserDetails
{
    private User user;

    public BoyingUserDetails(User user)
    {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        //返回当前用户的权限
        return Arrays.asList(new SimpleGrantedAuthority("TEST"));
    }

    @Override
    public String getPassword()
    {
        return user.getPassword();
    }

    @Override
    public String getUsername()
    {
        return user.getUsername();
    }

    //账号未过期
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    //账号未被锁定
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    //凭证未过期
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return user.getStatus();
    }

    public User getUser()
    {
        return user;
    }
}
