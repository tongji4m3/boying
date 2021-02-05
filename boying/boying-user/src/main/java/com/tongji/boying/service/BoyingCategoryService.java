package com.tongji.boying.service;

import com.tongji.boying.model.BoyingCategory;

import java.util.List;
import java.util.Map;

public interface BoyingCategoryService {
    List<BoyingCategory> categories();

    BoyingCategory getCategory(Integer id);
}
