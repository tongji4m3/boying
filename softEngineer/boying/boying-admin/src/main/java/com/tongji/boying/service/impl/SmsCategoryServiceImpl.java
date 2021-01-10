package com.tongji.boying.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.showParam.SmsCategoryParam;
import com.tongji.boying.mapper.BoyingCategoryMapper;
import com.tongji.boying.model.BoyingCategory;
import com.tongji.boying.model.BoyingCategoryExample;
import com.tongji.boying.service.SmsCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmsCategoryServiceImpl implements SmsCategoryService {
    @Autowired
    private BoyingCategoryMapper categoryMapper;

    @Override
    public void create(SmsCategoryParam param) {
        BoyingCategoryExample categoryExample = new BoyingCategoryExample();
        BoyingCategoryExample.Criteria criteria = categoryExample.createCriteria();
        criteria.andNameEqualTo(param.getName());
        List<BoyingCategory> categories = categoryMapper.selectByExample(categoryExample);
        if (CollUtil.isNotEmpty(categories)) {
            Asserts.fail("目录名称不能重复!");
        }
        BoyingCategory category = new BoyingCategory();
        BeanUtils.copyProperties(param, category);
        int count = categoryMapper.insertSelective(category);
        if(count==0) Asserts.fail("创建失败！");
    }

    @Override
    public void update(Integer id, SmsCategoryParam param) {
        BoyingCategoryExample categoryExample = new BoyingCategoryExample();
        BoyingCategoryExample.Criteria criteria = categoryExample.createCriteria();
        criteria.andNameEqualTo(param.getName());
        List<BoyingCategory> categories = categoryMapper.selectByExample(categoryExample);
        if (CollUtil.isNotEmpty(categories)) {
            Asserts.fail("目录名称不能重复!");
        }

        BoyingCategory category = new BoyingCategory();

        BeanUtils.copyProperties(param, category);
        category.setId(id);
        int count = categoryMapper.updateByPrimaryKeySelective(category);
        if(count==0) Asserts.fail("更新失败！");
    }

    @Override
    public void delete(Integer id) {
        BoyingCategory boyingCategory = categoryMapper.selectByPrimaryKey(id);
        boyingCategory.setAdminDelete(true);
        int count = categoryMapper.updateByPrimaryKeySelective(boyingCategory);
        if(count==0) Asserts.fail("删除失败！");
    }

    @Override
    public List<BoyingCategory> list() {
        List<BoyingCategory> categoryList = categoryMapper.selectByExample(new BoyingCategoryExample());
        if (ObjectUtil.isEmpty(categoryList)) Asserts.fail("无目录!");
        return categoryList;
    }

    @Override
    public BoyingCategory getCategory(Integer id) {
        BoyingCategory boyingCategory = categoryMapper.selectByPrimaryKey(id);
        if(boyingCategory==null) Asserts.fail("演出目录不存在！");
        return boyingCategory;
    }

    @Override
    public void recover(Integer id) {
        BoyingCategory boyingCategory = categoryMapper.selectByPrimaryKey(id);
        boyingCategory.setAdminDelete(false);
        int count = categoryMapper.updateByPrimaryKeySelective(boyingCategory);
        if(count==0) Asserts.fail("恢复失败！");
    }
}
