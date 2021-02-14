package com.tongji.boying.dao;

import com.tongji.boying.model.AdminMenu;
import com.tongji.boying.model.AdminResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义后台角色管理Dao
 */
public interface UmsRoleDao
{
    /**
     * 根据后台用户ID获取菜单
     */
    List<AdminMenu> getMenuList(@Param("userId") Integer userId);

    /**
     * 根据角色ID获取菜单
     */
    List<AdminMenu> getMenuListByRoleId(@Param("roleId") Integer roleId);

    /**
     * 根据角色ID获取资源
     */
    List<AdminResource> getResourceListByRoleId(@Param("roleId") Integer roleId);
}
