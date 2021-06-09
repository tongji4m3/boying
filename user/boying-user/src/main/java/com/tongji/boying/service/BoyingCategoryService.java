package com.tongji.boying.service;

import com.tongji.boying.model.BoyingCategory;

import java.util.List;

public interface BoyingCategoryService {
    List<BoyingCategory> categories();

    BoyingCategory getCategory(Integer id);
}
