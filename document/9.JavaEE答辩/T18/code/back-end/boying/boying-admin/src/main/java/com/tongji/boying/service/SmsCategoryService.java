package com.tongji.boying.service;

import com.tongji.boying.dto.SmsCategoryParam;
import com.tongji.boying.model.Category;

import java.util.List;

/**
 * 后台演出目录管理Service
 */
public interface SmsCategoryService {
    /**
     * 添加演出目录
     */
    int create(SmsCategoryParam param);

    /**
     * 修改演出目录
     */
    int update(Integer id, SmsCategoryParam param);

    /**
     * 批量删除演出目录
     */
    int delete(List<Integer> ids);

    /**
     * 删除演出目录
     */
    int delete(Integer id);

    /**
     * 获取所有演出目录列表
     */
    List<Category> list();

    /**
     * 获取所有演出一级目录列表
     */
    List<Category> listParent();

    /**
     * 获取某个演出目录列表
     */
    Category getCategory(Integer id);

    /**
     * 获取某个演出目录的子目录
     */
    List<Category> getChildrenCategory(Integer id);
}
