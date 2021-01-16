package com.tongji.boying.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.categoryParam.BoyingCategoryReturn;
import com.tongji.boying.dto.showParam.SmsCategoryParam;
import com.tongji.boying.mapper.BoyingCategoryMapper;
import com.tongji.boying.model.BoyingCategory;
import com.tongji.boying.model.BoyingCategoryExample;
import com.tongji.boying.service.SmsCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
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
        if (count == 0) Asserts.fail("创建失败！");
    }

    @Override
    public void update(Integer id, SmsCategoryParam param) {
        BoyingCategoryExample categoryExample = new BoyingCategoryExample();
        BoyingCategoryExample.Criteria criteria = categoryExample.createCriteria();
        criteria.andNameEqualTo(param.getName());
        List<BoyingCategory> categories = categoryMapper.selectByExample(categoryExample);
        if (CollUtil.isNotEmpty(categories)) {
            if (!categories.get(0).getId().equals(id)) {
                Asserts.fail("目录名称重复！");
            }
        }

        BoyingCategory category = new BoyingCategory();
        BeanUtils.copyProperties(param, category);
        category.setId(id);
        int count = categoryMapper.updateByPrimaryKeySelective(category);
        if (count == 0) Asserts.fail("更新失败！");
    }

    @Override
    public void delete(Integer id) {
        BoyingCategory boyingCategory = categoryMapper.selectByPrimaryKey(id);
        boyingCategory.setAdminDelete(1);
        int count = categoryMapper.updateByPrimaryKeySelective(boyingCategory);
        if (count == 0) Asserts.fail("删除失败！");
    }

    @Override
    public List<BoyingCategoryReturn> list() {
        BoyingCategoryExample example = new BoyingCategoryExample();
        example.setOrderByClause("weight desc");
        List<BoyingCategory> categoryList = categoryMapper.selectByExample(example);
        if (ObjectUtil.isEmpty(categoryList)) Asserts.fail("无目录!");


        List<BoyingCategoryReturn> boyingCategoryReturnList = new LinkedList<>();
        for (BoyingCategory boyingCategory : categoryList) {
            BoyingCategoryReturn boyingCategoryReturn = new BoyingCategoryReturn();
            BeanUtils.copyProperties(boyingCategory, boyingCategoryReturn);
            if (boyingCategory.getAdminDelete() == 1) {
                boyingCategoryReturn.setAdminDelete(true);
            }
            else {
                boyingCategoryReturn.setAdminDelete(false);
            }
            boyingCategoryReturnList.add(boyingCategoryReturn);
        }
        return boyingCategoryReturnList;
    }

    @Override
    public BoyingCategoryReturn getCategory(Integer id) {
        BoyingCategory boyingCategory = categoryMapper.selectByPrimaryKey(id);
        if (boyingCategory == null) Asserts.fail("演出目录不存在！");
//        BeanUtils.copyProperties(param, user);
        BoyingCategoryReturn boyingCategoryReturn = new BoyingCategoryReturn();
        BeanUtils.copyProperties(boyingCategory, boyingCategoryReturn);
        if (boyingCategory.getAdminDelete() == 1) {
            boyingCategoryReturn.setAdminDelete(true);
        }
        else {
            boyingCategoryReturn.setAdminDelete(false);
        }
        return boyingCategoryReturn;
    }

    @Override
    public void recover(Integer id) {
        BoyingCategory boyingCategory = categoryMapper.selectByPrimaryKey(id);
        boyingCategory.setAdminDelete(0);
        int count = categoryMapper.updateByPrimaryKeySelective(boyingCategory);
        if (count == 0) Asserts.fail("恢复失败！");
    }
}
