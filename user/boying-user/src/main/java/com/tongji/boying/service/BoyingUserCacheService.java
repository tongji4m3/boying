package com.tongji.boying.service;

import com.tongji.boying.model.BoyingUser;

/**
 * 用户信息缓存业务类
 */
public interface BoyingUserCacheService {
    void delUser(int UserId);

    BoyingUser getUser(String username);

    BoyingUser getUserByTelephone(String telephone);

    void setUser(BoyingUser BoyingUser);

    void setAuthCode(String telephone, String authCode);

    String getAuthCode(String telephone);

    void delAuthCode(String telephone);
}
