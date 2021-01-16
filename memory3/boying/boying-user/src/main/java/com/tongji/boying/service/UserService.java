package com.tongji.boying.service;

import com.tongji.boying.dto.userParam.*;
import com.tongji.boying.model.BoyingUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户管理Service
 *
 * @author tongji4m3
 */
public interface UserService {
    void register(UserRegisterParam param);

    String login(UsernameLoginParam param);

    String telephoneLogin(TelephoneLoginParam param);

    String authCodeLogin(AuthCodeLoginParam param);

    void updateInfo(UpdateInfoParam param);

    void updatePassword(UpdatePasswordParam param);

    /**
     * 根据用户名获取用户
     */
    BoyingUser getByUsername(String username);

    /**
     * 生成验证码
     */
    void generateAuthCode(String telephone);

    /**
     * 获取当前登录用户
     */
    BoyingUser getCurrentUser();

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 刷新token
     */
    String refreshToken(String token);
}
