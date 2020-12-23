package com.tongji.boying.controller;

import cn.hutool.core.util.ObjectUtil;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.dto.SmsCategoryParam;
import com.tongji.boying.model.Category;
import com.tongji.boying.service.SmsCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@Api(tags = "SmsCategoryController", description = "后台演出目录管理")
@RequestMapping("/category")
public class SmsCategoryController
{
    @Autowired
    private SmsCategoryService categoryService;

    @ApiOperation("添加目录")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@Validated @RequestBody SmsCategoryParam param)
    {
        int count = categoryService.create(param);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
    @ApiOperation("删除目录")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Integer id)
    {
        System.out.println(id);
        int count = categoryService.delete(id);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed("要删除的目录不存在!");
    }

    @ApiOperation("修改目录")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Integer id, @Validated @RequestBody SmsCategoryParam param)
    {
        int count = categoryService.update(id, param);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed("要修改的目录不存在!");
    }

    @ApiOperation("获取所有目录")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Category>> listAll()
    {
        List<Category> categoryList = categoryService.list();
        if(ObjectUtil.isEmpty(categoryList)) return CommonResult.failed("无目录!");
        return CommonResult.success(categoryList);
    }

    @ApiOperation("获取所有一级目录")
    @RequestMapping(value = "/listParent", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Category>> listParent()
    {
        List<Category> categoryList = categoryService.listParent();
        if(ObjectUtil.isEmpty(categoryList)) return CommonResult.failed("无目录!");
        return CommonResult.success(categoryList);
    }

    @ApiOperation("获取某个目录")
    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Category> getCategory(@PathVariable Integer id)
    {
        Category category = categoryService.getCategory(id);
        return CommonResult.success(category);
    }

    @ApiOperation("获取某个一级目录的所有二级目录")
    @RequestMapping(value = "/getChildren/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Category>> getChildrenCategory(@PathVariable Integer id)
    {
        List<Category> category = categoryService.getChildrenCategory(id);
        return CommonResult.success(category);
    }
}