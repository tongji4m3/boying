package com.tongji.boying.service.impl;

import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.common.service.RedisService;
import com.tongji.boying.mapper.BoyingCategoryMapper;
import com.tongji.boying.model.BoyingCategory;
import com.tongji.boying.service.BoyingCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BoyingCategoryServiceImpl implements BoyingCategoryService {
    @Resource
    private BoyingCategoryMapper boyingCategoryMapper;

    @Override
    public List<BoyingCategory> categories() {
        return boyingCategoryMapper.selectList();
    }

    @Override
    public BoyingCategory getCategory(Integer id) {
        BoyingCategory boyingCategory = boyingCategoryMapper.selectByPrimaryKey(id);
        if (boyingCategory == null || boyingCategory.getAdminDelete()) Asserts.fail("该演出目录不存在！");
        return boyingCategory;
    }
}
