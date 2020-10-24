package com.tongji.boying.service;

import com.tongji.boying.model.Address;
import com.tongji.boying.model.Category;

import java.util.List;

public interface CategoryService
{
    /**
     * 如果parentId=0,则返回当前所有的父级菜单
     * 如果parentId!=0,返回某父级菜单的所有子菜单
     */
    List<Category> categoryList(int parentId);

}
