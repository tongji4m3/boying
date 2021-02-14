package com.tongji.boying.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.UmsResourceCategoryParam;
import com.tongji.boying.mapper.AdminCategoryMapper;
import com.tongji.boying.model.AdminCategory;
import com.tongji.boying.model.AdminCategoryExample;
import com.tongji.boying.service.UmsCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 后台资源分类管理Service实现类
 */
@Service
public class UmsCategoryServiceImpl implements UmsCategoryService
{
    @Autowired
    private AdminCategoryMapper AdminCategoryMapper;

    @Override
    public List<AdminCategory> listAll()
    {
        AdminCategoryExample example = new AdminCategoryExample();
        example.setOrderByClause("weight desc");
        return AdminCategoryMapper.selectByExample(example);
    }

    @Override
    public int create(UmsResourceCategoryParam param)
    {
        checkResourceCategoryParam(param, -1);

        AdminCategory AdminCategory = new AdminCategory();
        BeanUtils.copyProperties(param, AdminCategory);
        AdminCategory.setCreateTime(new Date());
        return AdminCategoryMapper.insertSelective(AdminCategory);
    }

    private void checkResourceCategoryParam(UmsResourceCategoryParam param, Integer id)
    {
        AdminCategoryExample AdminCategoryExample = new AdminCategoryExample();
        AdminCategoryExample.Criteria criteria = AdminCategoryExample.createCriteria();
        criteria.andNameEqualTo(param.getName());
        if(id!=-1)
        {
            criteria.andIdNotEqualTo(id);
        }
        List<AdminCategory> resourceCategories = AdminCategoryMapper.selectByExample(AdminCategoryExample);
        if (ObjectUtil.isNotEmpty(resourceCategories))
        {
            //说明有重名资源分类名称
            Asserts.fail("资源分类名称重复!");
        }
    }

    @Override
    public int update(Integer id, UmsResourceCategoryParam param)
    {
        if(AdminCategoryMapper.selectByPrimaryKey(id)==null)
        {
            Asserts.fail("要修改的资源分类Id不存在!");
        }
        checkResourceCategoryParam(param, id);
        AdminCategory AdminCategory = new AdminCategory();
        BeanUtils.copyProperties(param, AdminCategory);
        AdminCategory.setId(id);
        return AdminCategoryMapper.updateByPrimaryKeySelective(AdminCategory);
    }

    @Override
    public AdminCategory getItem(Integer id)
    {
        return AdminCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(Integer id)
    {
        return AdminCategoryMapper.deleteByPrimaryKey(id);
    }
}
