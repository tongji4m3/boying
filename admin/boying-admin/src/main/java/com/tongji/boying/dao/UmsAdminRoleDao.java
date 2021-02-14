package com.tongji.boying.dao;

import com.tongji.boying.model.AdminRole;
import com.tongji.boying.model.AdminResource;
import com.tongji.boying.model.AdminRole;
import com.tongji.boying.model.AdminUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义后台用户与角色管理Dao
 */
public interface UmsAdminRoleDao
{
    /**
     * 批量插入用户角色关系
     */
    int insertList(@Param("list") List<AdminUserRole> adminUserRoles);

    /**
     * 获取用于所有角色
     */
    List<AdminRole> getRoleList(@Param("userId") Integer userId);

    /**
     * 获取用户所有可访问资源
     */
    List<AdminResource> getResourceList(@Param("userId") Integer userId);

    /**
     * 获取资源相关用户ID列表
     */
    List<Integer> getAdminIdList(@Param("resourceId") Integer resourceId);
}
