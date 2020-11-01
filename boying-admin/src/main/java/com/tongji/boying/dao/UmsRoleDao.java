package com.tongji.boying.dao;

import com.tongji.boying.model.Menu;
import com.tongji.boying.model.Resource;
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
    List<Menu> getMenuList(@Param("adminId") Integer adminId);

    /**
     * 根据角色ID获取菜单
     */
    List<Menu> getMenuListByRoleId(@Param("roleId") Integer roleId);

    /**
     * 根据角色ID获取资源
     */
    List<Resource> getResourceListByRoleId(@Param("roleId") Integer roleId);
}
