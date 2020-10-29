package com.tongji.boying.service;


import com.tongji.boying.model.Menu;

import java.util.List;
import java.util.Map;

/**
 * 后台菜单管理Service
 */
public interface UmsMenuService {
    /**
     * 创建后台菜单
     */
    int create(Menu umsMenu);

    /**
     * 修改后台菜单
     */
    int update(Integer id, Menu umsMenu);

    /**
     * 根据ID获取菜单详情
     */
    Menu getItem(Integer id);

    /**
     * 根据ID删除菜单
     */
    int delete(Integer id);

    /**
     * 分页查询后台菜单
     */
    List<Menu> list(Integer parentId, Integer pageSize, Integer pageNum);

    /**
     * 树形结构返回所有菜单列表
     * @return
     */
    Map<Menu,List<Menu>> categoryMap();

    /**
     * 修改菜单显示状态
     */
    int updateHidden(Integer id, Boolean hidden);
}
