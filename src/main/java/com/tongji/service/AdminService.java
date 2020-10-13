package com.tongji.service;


import com.tongji.mbg.model.Admin;
import com.tongji.mbg.model.Permission;

import java.util.List;

/**
 * 后台管理员Service
 */
public interface AdminService
{
    /**
     * 根据用户名获取后台管理员
     */
    Admin getAdminByUsername(String username);

    /**
     * 注册功能
     */
    Admin register(Admin AdminParam);

    /**
     * 登录功能
     *
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     */
    List<Permission> getPermissionList(Integer adminId);
}
