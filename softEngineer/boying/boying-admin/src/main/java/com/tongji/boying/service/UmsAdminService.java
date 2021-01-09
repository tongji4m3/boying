package com.tongji.boying.service;

import com.tongji.boying.model.Admin;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 后台管理员Service
 */
public interface UmsAdminService {
    /**
     * 获取当前登录管理员
     */
    Admin getCurrentAdmin();


    /**
     * 登录功能
     *
     * @param username 管理员名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);


    /**
     * 获取管理员信息
     */
    UserDetails loadUserByUsername(String username);
}
