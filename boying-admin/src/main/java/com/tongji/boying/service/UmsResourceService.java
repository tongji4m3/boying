package com.tongji.boying.service;


import com.tongji.boying.model.Resource;

import java.util.List;

/**
 * 后台资源管理Service
 */
public interface UmsResourceService {
    /**
     * 添加资源
     */
    int create(Resource resource);

    /**
     * 修改资源
     */
    int update(Integer id, Resource resource);

    /**
     * 获取资源详情
     */
    Resource getItem(Integer id);

    /**
     * 删除资源
     */
    int delete(Integer id);

    /**
     * 分页查询资源
     */
    List<Resource> list(Integer categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum);

    /**
     * 查询全部资源
     */
    List<Resource> listAll();
}
