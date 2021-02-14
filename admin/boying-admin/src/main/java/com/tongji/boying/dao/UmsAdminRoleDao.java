package com.tongji.boying.dao;

import com.tongji.boying.model.AdminRole;
import com.tongji.boying.model.Resource;
import com.tongji.boying.model.Role;
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
    int insertList(@Param("list") List<AdminRole> adminRoleList);

    /**
     * 获取用于所有角色
     */
    List<Role> getRoleList(@Param("adminId") Integer adminId);

    /**
     * 获取用户所有可访问资源
     */
    List<Resource> getResourceList(@Param("adminId") Integer adminId);

    /**
     * 获取资源相关用户ID列表
     */
    List<Integer> getAdminIdList(@Param("resourceId") Integer resourceId);
}
