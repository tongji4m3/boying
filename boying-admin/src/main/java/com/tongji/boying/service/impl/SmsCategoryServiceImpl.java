package com.tongji.boying.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.SmsCategoryParam;
import com.tongji.boying.dto.UmsRoleParam;
import com.tongji.boying.mapper.CategoryMapper;
import com.tongji.boying.model.*;
import com.tongji.boying.service.SmsCategoryService;
import org.mapstruct.ObjectFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmsCategoryServiceImpl implements SmsCategoryService
{
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public int create(SmsCategoryParam param)
    {
        checkCategoryParam(param, -1);
        Category category = new Category();
        BeanUtils.copyProperties(param, category);
        return categoryMapper.insertSelective(category);
    }

    @Override
    public int update(Integer id, SmsCategoryParam param)
    {
        checkCategoryParam(param, id);
        Category category = new Category();

        BeanUtils.copyProperties(param, category);
        category.setCategoryId(id);
        return categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public int delete(List<Integer> ids)
    {
        CategoryExample example = new CategoryExample();
        example.createCriteria().andCategoryIdIn(ids);
        if (categoryMapper.selectByExample(example).size() != ids.size())
        {
            Asserts.fail("某些演出目录Id不存在!");
        }
        return categoryMapper.deleteByExample(example);
    }

    @Override
    public int delete(Integer id)
    {
        return categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Category> list()
    {
        return categoryMapper.selectByExample(new CategoryExample());
    }

    @Override
    public List<Category> listParent() {
        CategoryExample example = new CategoryExample();
        example.createCriteria().andParentIdEqualTo(0);
        return categoryMapper.selectByExample(example);
    }

    @Override
    public  Category getCategory(Integer id){
        return categoryMapper.selectByPrimaryKey(id);
    }


    private void checkCategoryParam(SmsCategoryParam param, Integer id)
    {
        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        criteria.andNameEqualTo(param.getName());
        if (id != -1)
        {
            criteria.andCategoryIdNotEqualTo(id);
        }
        List<Category> categories = categoryMapper.selectByExample(categoryExample);
        if (CollUtil.isNotEmpty(categories))
        {
            Asserts.fail("目录名称不能重复!");
        }
    }

    @Override
    public List<Category> getChildrenCategory(Integer id) {
        CategoryExample example = new CategoryExample();
        example.createCriteria().andParentIdEqualTo(id);
        return categoryMapper.selectByExample(example);
    }
}
