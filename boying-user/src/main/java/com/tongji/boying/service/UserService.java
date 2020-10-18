package com.tongji.boying.service;

import com.tongji.boying.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户管理Service
 *
 * @author tongji4m3
 */
public interface UserService
{
    /**
     * 根据用户名获取用户
     */
    User getByUsername(String username);

    /**
     * 根据用户Id获取用户
     */
    User getById(int id);

    /**
     * 用户注册
     *
     * @Transactional 注解管理事务
     */
    @Transactional
    void register(String username, String password, String telephone, String checkCode);

    /**
     * 生成验证码
     */
    String generateCheckCode(String telephone);

    /**
     * 修改密码
     */
    @Transactional
    void updatePassword(String telephone, String password, String checkCode);

    /**
     * 获取当前登录用户
     */
    User getCurrentUser();

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 登录后获取token
     */
    String login(String username, String password);

    /**
     * 刷新token
     */
    String refreshToken(String token);
}
