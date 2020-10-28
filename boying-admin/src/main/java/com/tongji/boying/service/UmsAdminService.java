package com.tongji.boying.service;

import com.tongji.boying.dto.AdminParam;
import com.tongji.boying.dto.AdminPasswordParam;
import com.tongji.boying.model.Admin;
import com.tongji.boying.model.Resource;
import com.tongji.boying.model.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 后台管理员Service
 */
public interface UmsAdminService {
    /**
     * 根据用户名获取后台管理员
     */
    Admin getAdminByUsername(String username);

    /**
     * 注册功能
     */
    Admin register(AdminParam adminParam);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username,String password);

    /**
     * 刷新token的功能
     * @param oldToken 旧的token
     */
    String refreshToken(String oldToken);

    /**
     * 根据用户id获取用户
     */
    Admin getItem(Integer id);

    /**
     * 根据用户名或昵称分页查询用户
     */
    List<Admin> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 修改指定用户信息
     */
    int update(Integer id, Admin admin);

    /**
     * 删除指定用户
     */
    int delete(Integer id);

    /**
     * 修改用户角色关系
     */
    @Transactional
    int updateRole(Integer adminId, List<Integer> roleIds);

    /**
     * 获取用户对于角色
     */
    List<Role> getRoleList(Integer adminId);

    /**
     * 获取指定用户的可访问资源
     */
    List<Resource> getResourceList(Integer adminId);



    /**
     * 修改密码
     */
    int updatePassword(String username,String oldPassword,String newPassword);

    /**
     * 修改密码
     */
    int updatePassword(AdminPasswordParam passwordParam);

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);
}
