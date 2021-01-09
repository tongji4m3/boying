package com.tongji.boying.service;

import com.tongji.boying.dto.adminParam.UsernameLoginParam;
import com.tongji.boying.model.BoyingAdmin;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 后台管理员Service
 */
public interface UmsAdminService {
    /**
     * 获取当前登录管理员
     */
    BoyingAdmin getCurrentAdmin();


    /**
     * 登录功能
     * @return 生成的JWT的token
     */
    String login(UsernameLoginParam param);

    /**
     * 获取管理员信息
     */
    UserDetails loadUserByUsername(String username);
}
