package com.tongji.boying.service.impl;

import com.tongji.boying.mapper.CategoryMapper;
import com.tongji.boying.model.Category;
import com.tongji.boying.model.CategoryExample;
import com.tongji.boying.service.ShowCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShowCategoryServiceImpl implements ShowCategoryService
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
            map.put(parent, categoryList(parent.getCategoryId()));
        }
        return map;
    }

    @Override
    public boolean isSonCategory(int id)
    {
        CategoryExample categoryExample = new CategoryExample();
        List<Category> categories = categoryMapper.selectByExample(categoryExample);
        if(categories.isEmpty())
        {
            //该目录不存在,自然不是子目录
            return false;
        }
        if(categories.get(0).getParentId()==0 || categories.get(0).getWeight()==0)
        {
            //是父级目录或不能显示,也不是子目录
            return false;
        }
        return true;
    }
}
