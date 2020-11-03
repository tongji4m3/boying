package com.tongji.boying.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.UmsResourceCategoryParam;
import com.tongji.boying.mapper.ResourceCategoryMapper;
import com.tongji.boying.model.ResourceCategory;
import com.tongji.boying.model.ResourceCategoryExample;
import com.tongji.boying.service.UmsResourceCategoryService;
import org.springframework.beans.BeanUtils;
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
    public List<ResourceCategory> listAll()
    {
        ResourceCategoryExample example = new ResourceCategoryExample();
        example.setOrderByClause("sort desc");
        return resourceCategoryMapper.selectByExample(example);
    }

    @Override
    public int create(UmsResourceCategoryParam param)
    {
        checkResourceCategoryParam(param, -1);

        ResourceCategory resourceCategory = new ResourceCategory();
        BeanUtils.copyProperties(param, resourceCategory);
        resourceCategory.setCreateTime(new Date());
        return resourceCategoryMapper.insertSelective(resourceCategory);
    }

    private void checkResourceCategoryParam(UmsResourceCategoryParam param, Integer id)
    {
        ResourceCategoryExample resourceCategoryExample = new ResourceCategoryExample();
        ResourceCategoryExample.Criteria criteria = resourceCategoryExample.createCriteria();
        criteria.andNameEqualTo(param.getName());
        if(id!=-1)
        {
            criteria.andResourceCategoryIdNotEqualTo(id);
        }
        List<ResourceCategory> resourceCategories = resourceCategoryMapper.selectByExample(resourceCategoryExample);
        if (ObjectUtil.isNotEmpty(resourceCategories))
        {
            //说明有重名资源分类名称
            Asserts.fail("资源分类名称重复!");
        }
    }

    @Override
    public int update(Integer id, UmsResourceCategoryParam param)
    {
        if(resourceCategoryMapper.selectByPrimaryKey(id)==null)
        {
            Asserts.fail("要修改的资源分类Id不存在!");
        }
        checkResourceCategoryParam(param, id);
        ResourceCategory resourceCategory = new ResourceCategory();
        BeanUtils.copyProperties(param, resourceCategory);
        resourceCategory.setResourceCategoryId(id);
        return resourceCategoryMapper.updateByPrimaryKeySelective(resourceCategory);
    }

    @Override
    public ResourceCategory getItem(Integer id)
    {
        return resourceCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(Integer id)
    {
        return resourceCategoryMapper.deleteByPrimaryKey(id);
    }
}
