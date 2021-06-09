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
public interface BoyingUserService {
    void register(UserRegisterParam param);

    String login(UsernameLoginParam param);

    String telephoneLogin(TelephoneLoginParam param);

    String authCodeLogin(AuthCodeLoginParam param);

    void updateInfo(UpdateInfoParam param);

    void updatePassword(UpdatePasswordParam param);

    BoyingUser getByUsername(String username);

    void generateAuthCode(String telephone);

    BoyingUser getCurrentUser();

    UserDetails loadUserByUsername(String username);

    String refreshToken(String token);
}
