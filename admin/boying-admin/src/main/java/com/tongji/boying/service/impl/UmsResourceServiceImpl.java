package com.tongji.boying.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.UmsResourceParam;
import com.tongji.boying.mapper.AdminCategoryMapper;
import com.tongji.boying.mapper.AdminResourceMapper;
import com.tongji.boying.model.AdminMenu;
import com.tongji.boying.model.AdminMenuExample;
import com.tongji.boying.model.AdminResource;
import com.tongji.boying.model.AdminResourceExample;
import com.tongji.boying.service.UmsAdminCacheService;
import com.tongji.boying.service.UmsResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台资源管理Service实现类
 */
@Service
public class UmsResourceServiceImpl implements UmsResourceService
{
    @Autowired
    private AdminResourceMapper AdminResourceMapper;
    @Autowired
    private UmsAdminCacheService adminCacheService;

    @Autowired
    private AdminCategoryMapper AdminCategoryMapper;

    @Override
    public int create(UmsResourceParam param)
    {
        checkResourceParam(param, -1);
        AdminResource AdminResource = new AdminResource();
        BeanUtils.copyProperties(param, AdminResource);
        AdminResource.setCreateTime(new Date());
        return AdminResourceMapper.insertSelective(AdminResource);
    }

    @Override
    public int update(Integer id, UmsResourceParam param)
    {
        if(AdminResourceMapper.selectByPrimaryKey(id)==null)
        {
            Asserts.fail("要修改的资源Id不存在!");
        }
        checkResourceParam(param, id);


        AdminResource AdminResource = new AdminResource();
        BeanUtils.copyProperties(param, AdminResource);
        AdminResource.setId(id);

        int count = AdminResourceMapper.updateByPrimaryKeySelective(AdminResource);
        adminCacheService.delResourceListByResource(id);
        return count;
    }

    private void checkResourceParam(UmsResourceParam param, Integer id)
    {
        //除要修改的外是否还存在重名的
        AdminResourceExample AdminResourceExample = new AdminResourceExample();
        AdminResourceExample.Criteria criteria = AdminResourceExample.createCriteria();
        criteria.andNameEqualTo(param.getName());
        if (id != -1)
        {
            criteria.andIdNotEqualTo(id);
        }
        List<AdminResource> resources = AdminResourceMapper.selectByExample(AdminResourceExample);
        if (CollUtil.isNotEmpty(resources))
        {
            //说明有重名资源名称
            Asserts.fail("资源名称重复!");
        }
    }

    @Override
    public AdminResource getItem(Integer id)
    {
        return AdminResourceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(Integer id)
    {
        int count = AdminResourceMapper.deleteByPrimaryKey(id);
        adminCacheService.delResourceListByResource(id);
        return count;
    }

    @Override
    public List<AdminResource> list(Integer categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum)
    {
        PageHelper.startPage(pageNum, pageSize);
        AdminResourceExample example = new AdminResourceExample();
        AdminResourceExample.Criteria criteria = example.createCriteria();
        if (categoryId != null)
        {
            criteria.andIdEqualTo(categoryId);
        }
        if (StrUtil.isNotEmpty(nameKeyword))
        {
            criteria.andNameLike('%' + nameKeyword + '%');
        }
        if (StrUtil.isNotEmpty(urlKeyword))
        {
            criteria.andUrlLike('%' + urlKeyword + '%');
        }
        return AdminResourceMapper.selectByExample(example);
    }

    @Override
    public List<AdminResource> listAdminResource(Integer categoryId)
    {
        AdminResourceExample example = new AdminResourceExample();
        AdminResourceExample.Criteria criteria = example.createCriteria();
        if (categoryId != null)
        {
            criteria.andCategoryIdEqualTo(categoryId);
        }
        return AdminResourceMapper.selectByExample(example);
    }

    @Override
    public List<AdminResource> listAll()
    {
        AdminResourceExample adminResourceExample = new AdminResourceExample();
        adminResourceExample.setOrderByClause("weight desc");
        return AdminResourceMapper.selectByExample(adminResourceExample);
    }

}
