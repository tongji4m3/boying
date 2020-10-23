package com.tongji.boying.service;

import com.tongji.boying.model.User;

/**
 * 用户信息缓存业务类
 */
public interface UserCacheService
{
    /**
     * 删除用户缓存
     */
    void delUser(int UserId);

    /**
     * 获取用户缓存
     */
    User getUser(String username);

    /**
     * 根据手机号获取用户缓存
     */
    User getUserByTelephone(String telephone);



    /**
     * 设置用户缓存
     */
    void setUser(User User);

    /**
     * 设置验证码
     */
    void setAuthCode(String telephone, String authCode);

    /**
     * 获取验证码
     */
    String getAuthCode(String telephone);

    /**
     * 获取验证码
     */
    void delAuthCode(String telephone);
}
