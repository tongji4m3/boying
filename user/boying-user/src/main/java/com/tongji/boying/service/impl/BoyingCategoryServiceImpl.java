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

    @Autowired
    private RedisService redisService;

    @Override
    public List<BoyingCategory> categories() {
//        List<BoyingCategory> categories = (List<BoyingCategory>)redisService.get("boying_categories");
//        if ( categories != null) {
//            return categories;
//        }

        List<BoyingCategory> categories = boyingCategoryMapper.selectList();

//        redisService.set("boying_categories",categories);

        if (categories == null || categories.size() == 0) {
            Asserts.fail("演出目录为空！");
        }
        return categories;
    }

    @Override
    public BoyingCategory getCategory(Integer id) {
        BoyingCategory boyingCategory = boyingCategoryMapper.selectByPrimaryKey(id);
        if (boyingCategory == null) Asserts.fail("演出目录不存在！");
        if (boyingCategory.getAdminDelete()) Asserts.fail("该演出目录不存在！");
        return boyingCategory;
    }
}
