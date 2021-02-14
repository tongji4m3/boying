package com.tongji.boying.controller;

import cn.hutool.core.util.ObjectUtil;
import com.tongji.boying.common.api.CommonPage;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.dto.UmsMenuParam;
import com.tongji.boying.model.AdminMenu;
import com.tongji.boying.service.UmsMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 后台菜单管理Controller
 */
@Controller
@Api(tags = "UmsMenuController", description = "后台菜单管理")
@RequestMapping("/AdminMenu")
public class UmsMenuController
{

    @Autowired
    private UmsMenuService menuService;

    @ApiOperation("添加后台菜单")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@Validated @RequestBody UmsMenuParam param)
    {
        int count = menuService.create(param);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        else
        {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改后台菜单")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Integer id,
                               @Validated @RequestBody UmsMenuParam param)
    {
        int count = menuService.update(id, param);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        else
        {
            return CommonResult.failed("该菜单不存在");
        }
    }

    @ApiOperation("根据ID获取菜单详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<AdminMenu> getItem(@PathVariable Integer id)
    {
        AdminMenu AdminMenu = menuService.getItem(id);
        if(AdminMenu==null) return CommonResult.failed("该菜单不存在");
        return CommonResult.success(AdminMenu);
    }

    @ApiOperation("根据ID删除后台菜单")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Integer id)
    {
        int count = menuService.delete(id);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        else
        {
            return CommonResult.failed("该菜单不存在");
        }
    }

    @ApiOperation("分页查询后台菜单")
    @RequestMapping(value = "/list/{parentId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<AdminMenu>> list(@PathVariable Integer parentId,
                                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum)
    {
        //根据parentId进行查询,所以可以查所有一级菜单,也可以查某一级菜单的所有二级菜单
        List<AdminMenu> menuList = menuService.list(parentId, pageSize, pageNum);
        if(ObjectUtil.isEmpty(menuList)) return CommonResult.failed("无菜单信息");
        return CommonResult.success(CommonPage.restPage(menuList));
    }

    @ApiOperation("树形结构返回所有菜单列表")
    @RequestMapping(value = "/treeList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Map<AdminMenu, List<AdminMenu>>> treeList()
    {
        Map<AdminMenu, List<AdminMenu>> map = menuService.categoryMap();
        if(ObjectUtil.isEmpty(map)) return CommonResult.failed("无菜单信息");
        return CommonResult.success(map);
    }

    @ApiOperation("修改菜单显示状态")
    @RequestMapping(value = "/updateHidden/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateHidden(@PathVariable Integer id, @RequestParam("hidden") Boolean hidden)
    {
        int count = menuService.updateHidden(id, hidden);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        else
        {
            return CommonResult.failed("该菜单不存在");
        }
    }
}
