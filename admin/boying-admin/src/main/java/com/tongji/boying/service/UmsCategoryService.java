package com.tongji.boying.service;


import com.tongji.boying.dto.UmsResourceCategoryParam;
import com.tongji.boying.model.AdminResource;
import com.tongji.boying.model.AdminCategory;

import java.util.List;

/**
 * 后台资源分类管理Service
 */
public interface UmsCategoryService
{

    /**
     * 获取所有资源分类
     */
    List<AdminCategory> listAll();


    /**
     * 删除资源分类
     */
    int delete(Integer id);

    /**
     * 创建资源分类
     */
    int create(UmsResourceCategoryParam param);

    /**
     * 修改资源分类
     */
    int update(Integer id, UmsResourceCategoryParam param);

    AdminCategory getItem(Integer id);
}
