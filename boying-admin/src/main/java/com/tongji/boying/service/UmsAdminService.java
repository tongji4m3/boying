package com.tongji.boying.service;

import com.tongji.boying.dto.UmsAdminInfoParam;
import com.tongji.boying.dto.UmsAdminRegisterParam;
import com.tongji.boying.model.Admin;
import com.tongji.boying.model.Resource;
import com.tongji.boying.model.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 后台管理员Service
 */
public interface UmsAdminService
{
    /**
     * 根据管理员名获取后台管理员
     */
    Admin getAdminByUsername(String username);

    /**
     * 获取当前登录管理员
     */
    Admin getCurrentAdmin();

    /**
     * 注册功能
     */
    Admin register(UmsAdminRegisterParam param);

    /**
     * 登录功能
     *
     * @param username 管理员名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    /**
     * 刷新token的功能
     *
     * @param oldToken 旧的token
     */
    String refreshToken(String oldToken);

    /**
     * 根据管理员id获取管理员
     */
    Admin getItem(Integer id);

    /**
     * 根据管理员名分页查询管理员
     */
    List<Admin> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 修改指定管理员信息
     */
    int update(Integer id, UmsAdminInfoParam param);

    /**
     * 删除指定管理员
     */
    int delete(Integer id);

    /**
     * 修改管理员角色关系
     */
    @Transactional
    int updateRole(Integer adminId, List<Integer> roleIds);

    /**
     * 获取管理员对应角色
     */
    List<Role> getRoleList(Integer adminId);

    /**
     * 获取指定管理员的可访问资源
     */
    List<Resource> getResourceList(Integer adminId);


    /**
     * 获取管理员信息
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 更新管理员密码
     * @param username
     * @param password
     * @return
     */
    int updatePassword(String username, String password);

    int updatePassword(String username, String password, String newPassword);
}
