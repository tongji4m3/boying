package com.tongji.boying.controller;

import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.dto.showParam.SmsCategoryParam;
import com.tongji.boying.model.BoyingCategory;
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
public class SmsCategoryController {
    @Autowired
    private SmsCategoryService categoryService;

    @ApiOperation("获取所有目录")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<BoyingCategory>> listAll() {
        return CommonResult.success(categoryService.list());
    }


    @ApiOperation("添加目录")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@Validated @RequestBody SmsCategoryParam param) {
        categoryService.create(param);
        return CommonResult.success("创建目录成功！");
    }

    @ApiOperation("删除目录")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return CommonResult.success("删除目录成功！");
    }
    @ApiOperation("恢复目录")
    @RequestMapping(value = "/recover/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult recover(@PathVariable Integer id) {
        categoryService.recover(id);
        return CommonResult.success("恢复目录成功！");
    }

    @ApiOperation("修改目录")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Integer id, @Validated @RequestBody SmsCategoryParam param) {
        categoryService.update(id, param);
        return CommonResult.success("修改成功！");
    }


    @ApiOperation("获取某个目录")
    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<BoyingCategory> getCategory(@PathVariable Integer id) {
        return CommonResult.success(categoryService.getCategory(id));
    }
}
