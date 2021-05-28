package com.tongji.boying.service.impl;

import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.mapper.BoyingCategoryMapper;
import com.tongji.boying.model.BoyingCategory;
import com.tongji.boying.model.BoyingCategoryExample;
import com.tongji.boying.service.ShowCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShowCategoryServiceImpl implements ShowCategoryService {
    @Autowired
    private BoyingCategoryMapper categoryMapper;

    @Override
    public List<BoyingCategory> categories() {
        BoyingCategoryExample example = new BoyingCategoryExample();
        example.createCriteria().andAdminDeleteEqualTo(false);
        example.setOrderByClause("weight desc");
        List<BoyingCategory> categories = categoryMapper.selectByExample(example);
        if (categories==null || categories.size() == 0) {
            Asserts.fail("演出目录为空！");
        }
        return categories;
    }

    @Override
    public BoyingCategory getCategory(Integer id) {
        BoyingCategory boyingCategory = categoryMapper.selectByPrimaryKey(id);
        if (boyingCategory == null) Asserts.fail("演出目录不存在！");
        if(boyingCategory.getAdminDelete()) Asserts.fail("该演出目录不存在！");
        return boyingCategory;
    }
}
