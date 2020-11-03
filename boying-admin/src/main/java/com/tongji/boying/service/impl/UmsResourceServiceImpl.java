package com.tongji.boying.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.UmsResourceParam;
import com.tongji.boying.mapper.ResourceCategoryMapper;
import com.tongji.boying.mapper.ResourceMapper;
import com.tongji.boying.model.Resource;
import com.tongji.boying.model.ResourceCategory;
import com.tongji.boying.model.ResourceCategoryExample;
import com.tongji.boying.model.ResourceExample;
import com.tongji.boying.service.UmsAdminCacheService;
import com.tongji.boying.service.UmsResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 后台资源管理Service实现类
 */
@Service
public class UmsResourceServiceImpl implements UmsResourceService
{
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private UmsAdminCacheService adminCacheService;

    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;

    @Override
    public int create(UmsResourceParam param)
    {
        checkResourceParam(param, -1);
        Resource resource = new Resource();
        BeanUtils.copyProperties(param, resource);
        resource.setCreateTime(new Date());
        return resourceMapper.insertSelective(resource);
    }

    @Override
    public int update(Integer id, UmsResourceParam param)
    {
        if(resourceMapper.selectByPrimaryKey(id)==null)
        {
            Asserts.fail("要修改的资源Id不存在!");
        }
        checkResourceParam(param, id);


        Resource resource = new Resource();
        BeanUtils.copyProperties(param, resource);
        resource.setResourceId(id);

        int count = resourceMapper.updateByPrimaryKeySelective(resource);
        adminCacheService.delResourceListByResource(id);
        return count;
    }

    private void checkResourceParam(UmsResourceParam param, Integer id)
    {
        //除要修改的外是否还存在重名的
        ResourceExample resourceExample = new ResourceExample();
        ResourceExample.Criteria criteria = resourceExample.createCriteria();
        criteria.andNameEqualTo(param.getName());
        if (id != -1)
        {
            criteria.andResourceIdNotEqualTo(id);
        }
        List<Resource> resources = resourceMapper.selectByExample(resourceExample);
        if (CollUtil.isNotEmpty(resources))
        {
            //说明有重名资源名称
            Asserts.fail("资源名称重复!");
        }
    }

    @Override
    public Resource getItem(Integer id)
    {
        return resourceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(Integer id)
    {
        int count = resourceMapper.deleteByPrimaryKey(id);
        adminCacheService.delResourceListByResource(id);
        return count;
    }

    @Override
    public List<Resource> list(Integer categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum)
    {
        PageHelper.startPage(pageNum, pageSize);
        ResourceExample example = new ResourceExample();
        ResourceExample.Criteria criteria = example.createCriteria();
        if (categoryId != null)
        {
            criteria.andResourceCategoryIdEqualTo(categoryId);
        }
        if (StrUtil.isNotEmpty(nameKeyword))
        {
            criteria.andNameLike('%' + nameKeyword + '%');
        }
        if (StrUtil.isNotEmpty(urlKeyword))
        {
            criteria.andUrlLike('%' + urlKeyword + '%');
        }
        return resourceMapper.selectByExample(example);
    }

    @Override
    public List<Resource> listAll()
    {
        return resourceMapper.selectByExample(new ResourceExample());
    }
}
