package com.tongji.boying.service;

import com.tongji.boying.dto.UmsAdminInfoParam;
import com.tongji.boying.dto.UmsAdminRegisterParam;
import com.tongji.boying.model.AdminUser;
import com.tongji.boying.model.AdminResource;
import com.tongji.boying.model.AdminRole;
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
    AdminUser getAdminByUsername(String username);

    /**
     * 获取当前登录管理员
     */
    AdminUser getCurrentAdmin();

    /**
     * 注册功能
     */
    AdminUser register(UmsAdminRegisterParam param);

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
    AdminUser getItem(Integer id);

    /**
     * 根据管理员名分页查询管理员
     */
    List<AdminUser> list(String keyword, Integer pageSize, Integer pageNum);

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
    int updateRole(Integer userId, List<Integer> roleIds);

    /**
     * 获取管理员对应角色
     */
    List<AdminRole> getRoleList(Integer adminId);

    /**
     * 获取指定管理员的可访问资源
     */
    List<AdminResource> getResourceList(Integer adminId);


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

    int deleteRole(Integer adminId, List<Integer> roleIds);
}
