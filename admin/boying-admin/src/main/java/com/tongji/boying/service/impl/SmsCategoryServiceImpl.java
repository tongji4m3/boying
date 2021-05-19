package com.tongji.boying.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.SmsCategoryParam;
//import com.tongji.boying.dto.UmsRoleParam;
import com.tongji.boying.mapper.BoyingCategoryMapper;
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
    private BoyingCategoryMapper categoryMapper;

    @Override
    public int create(SmsCategoryParam param)
    {
        checkCategoryParam(param, -1);
        BoyingCategory category = new BoyingCategory();
        BeanUtils.copyProperties(param, category);
        return categoryMapper.insertSelective(category);
    }

    @Override
    public int update(Integer id, SmsCategoryParam param)
    {
        checkCategoryParam(param, id);
        BoyingCategory category = new BoyingCategory();

        BeanUtils.copyProperties(param, category);
        category.setId(id);
        return categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public int delete(List<Integer> ids)
    {
        BoyingCategoryExample example = new BoyingCategoryExample();
        example.createCriteria().andIdIn(ids);
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
    public List<BoyingCategory> list()
    {
        BoyingCategoryExample boyingCategoryExample = new BoyingCategoryExample();
        boyingCategoryExample.setOrderByClause("weight desc");
        return categoryMapper.selectByExample(boyingCategoryExample);
    }

//    @Override
//    public List<BoyingCategory> listParent() {
//        BoyingCategoryExample example = new BoyingCategoryExample();
//        example.createCriteria().andParentIdEqualTo(0);
//        return categoryMapper.selectByExample(example);
//    }

    @Override
    public  BoyingCategory getCategory(Integer id){
        return categoryMapper.selectByPrimaryKey(id);
    }


    private void checkCategoryParam(SmsCategoryParam param, Integer id)
    {
        BoyingCategoryExample categoryExample = new BoyingCategoryExample();
        BoyingCategoryExample.Criteria criteria = categoryExample.createCriteria();
        criteria.andNameEqualTo(param.getName());
        if (id != -1)
        {
            criteria.andIdNotEqualTo(id);
        }
        List<BoyingCategory> categories = categoryMapper.selectByExample(categoryExample);
        if (CollUtil.isNotEmpty(categories))
        {
            Asserts.fail("目录名称不能重复!");
        }
    }

//    @Override
//    public List<BoyingCategory> getChildrenCategory(Integer id) {
//        BoyingCategoryExample example = new BoyingCategoryExample();
//        example.createCriteria().andParentIdEqualTo(id);
//        return categoryMapper.selectByExample(example);
//    }
}
