package com.tongji.boying.service.impl;

import com.tongji.boying.mapper.CategoryMapper;
import com.tongji.boying.model.Category;
import com.tongji.boying.model.CategoryExample;
import com.tongji.boying.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService
{
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> categoryList(int parentId)
    {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andWeightNotEqualTo(0).andParentIdEqualTo(parentId);
        categoryExample.setOrderByClause("weight desc");
        return categoryMapper.selectByExample(categoryExample);
    }

    @Override
    public Map<Category, List<Category>> categoryMap()
    {
        //用LinkedHashMap保持插入顺序,保证最后结果的权重
        Map<Category, List<Category>> map = new LinkedHashMap<>();
        List<Category> parents = categoryList(0);
        for (Category parent : parents)
        {
            map.put(parent, categoryList(parent.getParentId()));
        }
        return map;
    }

    @Override
    public boolean isSonCategory(int id)
    {
        CategoryExample categoryExample = new CategoryExample();
//        不显示的目录也不算子目录
        categoryExample.createCriteria().andCategoryIdEqualTo(id).andWeightNotEqualTo(0).andParentIdEqualTo(0);
        List<Category> categories = categoryMapper.selectByExample(categoryExample);
        //parentId=0说明是一级目录,则不为空
        //所以为空就是子目录
        return categories.isEmpty();
    }
}
