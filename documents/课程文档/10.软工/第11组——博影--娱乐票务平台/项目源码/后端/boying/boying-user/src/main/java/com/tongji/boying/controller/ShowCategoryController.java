package com.tongji.boying.controller;

import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.model.BoyingCategory;
import com.tongji.boying.service.ShowCategoryService;
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
@Api(tags = "ShowCategoryController", description = "查看演出菜单目录相关API")
@RequestMapping("/category")
public class ShowCategoryController {
    @Autowired
    private ShowCategoryService showCategoryService;


    @ApiOperation("获取菜单")
        @RequestMapping(value = "/categories", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<BoyingCategory>> getCategories() {
        return CommonResult.success(showCategoryService.categories());
    }

    @ApiOperation("获取某个目录")
    @RequestMapping(value = "/details/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<BoyingCategory> getCategory(@PathVariable Integer id) {
        return CommonResult.success(showCategoryService.getCategory(id));
    }
}
