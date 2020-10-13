package com.tongji.dao;


import com.tongji.mbg.model.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台用户与角色管理自定义Dao
 */
public interface AdminRoleDao
{

    /**
     * 获取用户所有权限(包括+-权限)
     */
    List<Permission> getPermissionList(@Param("adminId") Integer adminId);
}
