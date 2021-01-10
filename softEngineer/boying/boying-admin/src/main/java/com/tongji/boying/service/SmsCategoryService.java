package com.tongji.boying.service;

import com.tongji.boying.dto.showParam.SmsCategoryParam;
import com.tongji.boying.model.BoyingCategory;

import java.util.List;

/**
 * 后台演出目录管理Service
 */
public interface SmsCategoryService {
    /**
     * 添加演出目录
     */
    void create(SmsCategoryParam param);

    /**
     * 修改演出目录
     */
    void update(Integer id, SmsCategoryParam param);


    /**
     * 删除演出目录
     */
    void delete(Integer id);

    /**
     * 获取所有演出目录列表
     */
    List<BoyingCategory> list();


    /**
     * 获取某个演出目录列表
     */
    BoyingCategory getCategory(Integer id);

    void recover(Integer id);

}
