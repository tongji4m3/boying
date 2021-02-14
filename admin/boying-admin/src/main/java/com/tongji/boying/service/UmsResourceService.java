package com.tongji.boying.service;


import com.tongji.boying.dto.UmsResourceParam;
import com.tongji.boying.model.AdminResource;

import java.util.List;

/**
 * 后台资源管理Service
 */
public interface UmsResourceService
{
    /**
     * 添加资源
     *
     * @param AdminResource
     */
    int create(UmsResourceParam AdminResource);

    /**
     * 修改资源
     */
    int update(Integer id, UmsResourceParam AdminResource);

    /**
     * 获取资源详情
     */
    AdminResource getItem(Integer id);

    /**
     * 删除资源
     */
    int delete(Integer id);

    /**
     * 分页查询资源
     */
    List<AdminResource> list(Integer categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum);

    /**
     * 查询全部资源
     */
    List<AdminResource> listAll();
}
