package com.tongji.boying.controller;

import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.model.Category;
import com.tongji.boying.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 查看菜单目录Controller
 */
@Controller
@Api(tags = "CategoryController", description = "查看演出菜单目录")
@RequestMapping("/user/category")
public class CategoryController
{
    @Autowired
    private CategoryService categoryService;

    @ApiOperation("获取所有的父级菜单列表或某个父级菜单的所有二级子菜单")
    @RequestMapping(value = "/categoryList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Category>> getCategoryList(@RequestParam int parentId)
    {
        List<Category> categories = categoryService.categoryList(parentId);
        return CommonResult.success(categories);
    }
}
