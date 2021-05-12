package com.tongji.boying.controller;

import com.tongji.boying.common.api.CommonPage;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.dto.SmsCategoryParam;
import com.tongji.boying.dto.UmsResourceParam;
import com.tongji.boying.model.AdminResource;
import com.tongji.boying.model.BoyingCategory;
import com.tongji.boying.security.component.DynamicSecurityMetadataSource;
import com.tongji.boying.service.UmsResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台资源管理Controller
 */
@Controller
@Api(tags = "UmsResourceController", description = "后台资源管理")
@RequestMapping("/AdminResource")
public class UmsResourceController
{

    @Autowired
    private UmsResourceService resourceService;
    @Autowired
    private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;

    @ApiOperation("添加后台资源")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@Validated @RequestBody UmsResourceParam param)
    {
        int count = resourceService.create(param);
        dynamicSecurityMetadataSource.clearDataSource();
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        else
        {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改后台资源")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Integer id,
                               @Validated @RequestBody UmsResourceParam param)
    {
        int count = resourceService.update(id, param);
        dynamicSecurityMetadataSource.clearDataSource();
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        else
        {
            return CommonResult.failed("没有该后台资源");
        }
    }

    @ApiOperation("根据ID获取资源详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<AdminResource> getItem(@PathVariable Integer id)
    {
        AdminResource AdminResource = resourceService.getItem(id);
        if(AdminResource==null) return CommonResult.failed("没有该后台资源");
        return CommonResult.success(AdminResource);
    }

    @ApiOperation("根据ID删除后台资源")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Integer id)
    {
        int count = resourceService.delete(id);
        dynamicSecurityMetadataSource.clearDataSource();
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        else
        {
            return CommonResult.failed("没有该后台资源");
        }
    }

    @ApiOperation("分页模糊查询后台资源")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<AdminResource>> list(@RequestParam(required = false) Integer categoryId,
                                                   @RequestParam(required = false) String nameKeyword,
                                                   @RequestParam(required = false) String urlKeyword,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum)
    {
        List<AdminResource> resourceList = resourceService.list(categoryId, nameKeyword, urlKeyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(resourceList));
    }

    @ApiOperation("查询所有后台资源")
    @RequestMapping(value = "/listAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<AdminResource>> listAll()
    {
        List<AdminResource> resourceList = resourceService.listAll();
        return CommonResult.success(resourceList);
    }


    @ApiOperation("修改资源启用状态")
    @RequestMapping(value = "/changeStatus/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult changeStatus(@PathVariable Integer id)
    {
        AdminResource adminResource = resourceService.getItem(id);
        if(adminResource==null){
            return CommonResult.failed("目录不存在!");
        }
        if(adminResource.getStatus()){
            System.out.println("hellofalse");
            adminResource.setStatus(false);
        }else{
            System.out.println("hellotrue");
            adminResource.setStatus(true);
        }
        UmsResourceParam param=new UmsResourceParam();
        BeanUtils.copyProperties(adminResource,param);
        System.out.println(adminResource);
        System.out.println(param);
        resourceService.update(id,param);
        return CommonResult.success("成功!");
    }
}
