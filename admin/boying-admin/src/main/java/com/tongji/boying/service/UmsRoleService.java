package com.tongji.boying.service;

import com.tongji.boying.dto.UmsRoleParam;
import com.tongji.boying.model.AdminMenu;
import com.tongji.boying.model.AdminResource;
import com.tongji.boying.model.AdminRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 后台角色管理Service
 */
public interface UmsRoleService
{
    /**
     * 添加角色
     *
     * @param param
     */
    int create(UmsRoleParam param);

    /**
     * 修改角色信息
     */
    int update(Integer id, UmsRoleParam param);

    /**
     * 批量删除角色
     */
    int delete(List<Integer> ids);

    /**
     * 删除角色
     */
    int delete(Integer id);

    /**
     * 获取所有角色列表
     */
    List<AdminRole> list();

    /**
     * 分页获取角色列表
     */
    List<AdminRole> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 根据管理员ID获取对应菜单
     */
    List<AdminMenu> getMenuList(Integer adminId);

    /**
     * 获取角色相关菜单
     */
    List<AdminMenu> listMenu(Integer roleId);

    /**
     * 获取角色相关资源
     */
    List<AdminResource> listResource(Integer roleId);

    /**
     * 给角色分配菜单
     */
    @Transactional
    int allocMenu(Integer roleId, List<Integer> menuIds);

    /**
     * 给角色分配资源
     */
    @Transactional
    int allocResource(Integer roleId, List<Integer> resourceIds);

    List<AdminResource> getResourceList(Integer adminId);

    int updateStatus(Integer id, Boolean status);
}
