package com.tongji.boying.controller;

import com.tongji.boying.common.api.CommonPage;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.dto.UmsRoleParam;
import com.tongji.boying.model.Menu;
import com.tongji.boying.model.Resource;
import com.tongji.boying.model.Role;
import com.tongji.boying.service.UmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台用户角色管理
 */
@Controller
@Api(tags = "UmsRoleController", description = "后台用户角色管理")
@RequestMapping("/role")
public class UmsRoleController
{
    @Autowired
    private UmsRoleService roleService;

    @ApiOperation("添加角色")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@Validated @RequestBody UmsRoleParam param)
    {
        int count = roleService.create(param);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改角色")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Integer id, @Validated @RequestBody UmsRoleParam param)
    {
        int count = roleService.update(id, param);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("批量删除角色")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("ids") List<Integer> ids)
    {
        int count = roleService.delete(ids);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取所有角色")
    @RequestMapping(value = "/listAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<Role>> listAll()
    {
        List<Role> roleList = roleService.list();
        return CommonResult.success(roleList);
    }

    @ApiOperation("根据角色名称分页获取角色列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<Role>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum)
    {
        List<Role> roleList = roleService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(roleList));
    }

    @ApiOperation("修改角色状态")
    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateStatus(@PathVariable Integer id, @RequestParam(value = "status") Boolean status)
    {
        UmsRoleParam param = new UmsRoleParam();
        param.setStatus(status);
        int count = roleService.update(id, param);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取角色相关菜单")
    @RequestMapping(value = "/listMenu/{roleId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<Menu>> listMenu(@PathVariable Integer roleId)
    {
        List<Menu> roleList = roleService.listMenu(roleId);
        return CommonResult.success(roleList);
    }

    @ApiOperation("获取角色相关资源")
    @RequestMapping(value = "/listResource/{roleId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<Resource>> listResource(@PathVariable Integer roleId)
    {
        List<Resource> roleList = roleService.listResource(roleId);
        return CommonResult.success(roleList);
    }

    @ApiOperation("给角色分配菜单")
    @RequestMapping(value = "/allocMenu", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult allocMenu(@RequestParam Integer roleId, @RequestParam List<Integer> menuIds)
    {
        int count = roleService.allocMenu(roleId, menuIds);
        return CommonResult.success(count);
    }

    @ApiOperation("给角色分配资源")
    @RequestMapping(value = "/allocResource", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult allocResource(@RequestParam Integer roleId, @RequestParam List<Integer> resourceIds)
    {
        int count = roleService.allocResource(roleId, resourceIds);
        return CommonResult.success(count);
    }

}
