package com.tongji.boying.service.impl;

import com.tongji.boying.mapper.ResourceCategoryMapper;
import com.tongji.boying.model.ResourceCategory;
import com.tongji.boying.model.ResourceCategoryExample;
import com.tongji.boying.service.UmsResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 后台资源分类管理Service实现类
 */
@Service
public class UmsResourceCategoryServiceImpl implements UmsResourceCategoryService
{
    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;

    @Override
    public List<ResourceCategory> listAll() {
        ResourceCategoryExample example = new ResourceCategoryExample();
        example.setOrderByClause("sort desc");
        return resourceCategoryMapper.selectByExample(example);
    }

    @Override
    public int create(String name,Integer sort) {
        ResourceCategory resourceCategory = new ResourceCategory();
        resourceCategory.setCreateTime(new Date());
        resourceCategory.setName(name);
        resourceCategory.setSort(sort);
        return resourceCategoryMapper.insert(resourceCategory);
    }

    @Override
    public int update(Integer id, String name,Integer sort) {
        ResourceCategory resourceCategory = new ResourceCategory();
        resourceCategory.setResourceCategoryId(id);
        resourceCategory.setName(name);
        resourceCategory.setSort(sort);
        return resourceCategoryMapper.updateByPrimaryKeySelective(resourceCategory);
    }

    @Override
    public int delete(Integer id) {
        return resourceCategoryMapper.deleteByPrimaryKey(id);
    }
}
