package com.tongji.boying.service;

import com.tongji.boying.model.Category;

import java.util.List;
import java.util.Map;

public interface ShowCategoryService
{
    /**
     * 如果parentId=0,则返回当前所有的父级菜单
     * 如果parentId!=0,返回某父级菜单的所有子菜单
     */
    List<Category> categoryList(int parentId);

    String categoryName(int categoryId);

    Category getParentCategory(int categoryId);


    /**
     * 以Map结构获取所有商品分类 (父级菜单,该父级菜单的所有二级菜单)
     */
    Map<Category, List<Category>> categoryMap();

    /**
     * 判断是否是子目录
     */
    boolean isSonCategory(int id);
}
