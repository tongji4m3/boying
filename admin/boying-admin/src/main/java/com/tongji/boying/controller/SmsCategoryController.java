package com.tongji.boying.controller;

import cn.hutool.core.util.ObjectUtil;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.dto.SmsCategoryParam;
import com.tongji.boying.model.BoyingCategory;
import com.tongji.boying.service.SmsCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
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
    @ApiOperation("修改目录启用状态")
    @RequestMapping(value = "/admin_delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult admin_delete(@PathVariable Integer id)
    {
        System.out.println(id);
        BoyingCategory category = categoryService.getCategory(id);
        if(category==null){
            return CommonResult.failed("目录不存在!");
        }
        if(category.getAdminDelete()){
            System.out.println("false");
            category.setAdminDelete(false);
        }else{
            System.out.println("true");
            category.setAdminDelete(true);
        }
        SmsCategoryParam param=new SmsCategoryParam();
        BeanUtils.copyProperties(category,param);
        System.out.println(category);
        System.out.println(param);
        categoryService.update(id,param);
        return CommonResult.success("成功!");
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
    public CommonResult<List<BoyingCategory>> listAll()
    {
        List<BoyingCategory> categoryList = categoryService.list();
        if(ObjectUtil.isEmpty(categoryList))
        {
            return CommonResult.failed("无目录!");
        }
        return CommonResult.success(categoryList);
    }
//
//    @ApiOperation("获取所有一级目录")
//    @RequestMapping(value = "/listParent", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<List<BoyingCategory>> listParent()
//    {
//        List<BoyingCategory> categoryList = categoryService.listParent();
//        if(ObjectUtil.isEmpty(categoryList)) return CommonResult.failed("无目录!");
//        return CommonResult.success(categoryList);
//    }

    @ApiOperation("获取某个目录")
    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<BoyingCategory> getCategory(@PathVariable Integer id)
    {
        BoyingCategory category = categoryService.getCategory(id);
        return CommonResult.success(category);
    }

//    @ApiOperation("获取某个一级目录的所有二级目录")
//    @RequestMapping(value = "/getChildren/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<List<BoyingCategory>> getChildrenCategory(@PathVariable Integer id)
//    {
//        List<BoyingCategory> category = categoryService.getChildrenCategory(id);
//        return CommonResult.success(category);
//    }
}