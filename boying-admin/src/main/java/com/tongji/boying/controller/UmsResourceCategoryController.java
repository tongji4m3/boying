package com.tongji.boying.controller;

import cn.hutool.core.collection.CollUtil;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.dto.UmsResourceCategoryParam;
import com.tongji.boying.model.ResourceCategory;
import com.tongji.boying.model.ResourceCategory;
import com.tongji.boying.service.UmsResourceCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台资源分类管理Controller
 */
@Controller
@Api(tags = "UmsResourceCategoryController", description = "后台资源分类管理")
@RequestMapping("/resourceCategory")
public class UmsResourceCategoryController
{
    @Autowired
    private UmsResourceCategoryService resourceCategoryService;

    @ApiOperation("查询所有后台资源分类")
    @RequestMapping(value = "/listAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<ResourceCategory>> listAll()
    {
        List<ResourceCategory> resourceCategories = resourceCategoryService.listAll();
        if(CollUtil.isEmpty(resourceCategories))
        {
            return CommonResult.failed("后台资源分类不存在!");
        }
        return CommonResult.success(resourceCategories);
    }

    @ApiOperation("添加后台资源分类")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@Validated @RequestBody UmsResourceCategoryParam param)
    {
        int count = resourceCategoryService.create(param);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        else
        {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改后台资源分类")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Integer id,
                               @Validated @RequestBody UmsResourceCategoryParam param)
    {
        int count = resourceCategoryService.update(id, param);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        else
        {
            return CommonResult.failed("要删除的后台资源分类不存在!");
        }
    }

    @ApiOperation("根据ID删除后台资源分类")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Integer id)
    {
        int count = resourceCategoryService.delete(id);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        else
        {
            return CommonResult.failed("要删除的后台资源分类不存在!");
        }
    }

    @ApiOperation("根据ID获取资源详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<ResourceCategory> getItem(@PathVariable Integer id)
    {
        ResourceCategory resourceCategory = resourceCategoryService.getItem(id);
        if(resourceCategory==null) return CommonResult.failed("没有该后台资源分类");
        return CommonResult.success(resourceCategory);
    }
}
