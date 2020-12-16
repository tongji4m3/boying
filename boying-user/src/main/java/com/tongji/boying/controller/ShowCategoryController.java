package com.tongji.boying.controller;

import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.model.Category;
import com.tongji.boying.service.ShowCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 查看菜单目录Controller
 */
@Controller
@Api(tags = "ShowCategoryController", description = "查看演出菜单目录相关API")
@RequestMapping("/category")
public class ShowCategoryController
{
    @Autowired
    private ShowCategoryService showCategoryService;

    @ApiOperation("获取所有的父级菜单列表或某个父级菜单的所有二级子菜单")
    @RequestMapping(value = "/categoryList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<Category>> getCategoryList(@RequestBody int parentId)
    {
        List<Category> categories = showCategoryService.categoryList(parentId);
        return CommonResult.success(categories);
    }

    @ApiOperation("获取以Map结构获取所有商品分类 (父级菜单,该父级菜单的所有二级菜单)")
    @RequestMapping(value = "/categoryMap", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Map<Category, List<Category>>> getCategoryMap()
    {
        Map<Category, List<Category>> map = showCategoryService.categoryMap();
        return CommonResult.success(map);
    }
}
