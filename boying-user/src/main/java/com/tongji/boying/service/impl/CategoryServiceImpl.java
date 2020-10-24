package com.tongji.boying.service.impl;

import com.tongji.boying.mapper.CategoryMapper;
import com.tongji.boying.model.Category;
import com.tongji.boying.model.CategoryExample;
import com.tongji.boying.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
