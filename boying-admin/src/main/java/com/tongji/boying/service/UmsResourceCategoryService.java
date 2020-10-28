package com.tongji.boying.service;


import com.tongji.boying.model.ResourceCategory;

import java.util.List;

/**
 * 后台资源分类管理Service
 */
public interface UmsResourceCategoryService {

    /**
     * 获取所有资源分类
     */
    List<ResourceCategory> listAll();

    /**
     * 创建资源分类
     */
    int create(ResourceCategory resourceCategory);

    /**
     * 修改资源分类
     */
    int update(Integer id, ResourceCategory resourceCategory);

    /**
     * 删除资源分类
     */
    int delete(Integer id);
}
