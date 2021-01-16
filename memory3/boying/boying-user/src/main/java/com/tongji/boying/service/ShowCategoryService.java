package com.tongji.boying.service;

import com.tongji.boying.model.BoyingCategory;

import java.util.List;
import java.util.Map;

public interface ShowCategoryService {
    List<BoyingCategory> categories();

    BoyingCategory getCategory(Integer id);
}
