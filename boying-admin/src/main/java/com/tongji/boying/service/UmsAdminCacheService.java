package com.tongji.boying.service;


import com.tongji.boying.model.Admin;
import com.tongji.boying.model.Resource;

import java.util.List;

/**
 * 后台用户缓存操作类
 */
public interface UmsAdminCacheService
{
    /**
     * 删除后台用户缓存
     */
    void delAdmin(Integer adminId);

    /**
     * 删除后台用户资源列表缓存
     */
    void delResourceList(Integer adminId);

    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     */
    void delResourceListByRole(Integer roleId);

    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     */
    void delResourceListByRoleIds(List<Integer> roleIds);

    /**
     * 当资源信息改变时，删除资源项目后台用户缓存
     */
    void delResourceListByResource(Integer resourceId);

    /**
     * 获取缓存后台用户信息
     */
    Admin getAdmin(String username);

    /**
     * 设置缓存后台用户信息
     */
    void setAdmin(Admin admin);

    /**
     * 获取缓存后台用户资源列表
     */
    List<Resource> getResourceList(Integer adminId);

    /**
     * 设置后台用户资源列表
     */
    void setResourceList(Integer adminId, List<Resource> resourceList);
}
