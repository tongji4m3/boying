package com.tongji.boying.controller;

import com.tongji.boying.common.api.CommonPage;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.dto.UmsAdminInfoParam;
import com.tongji.boying.dto.UmsAdminRegisterParam;
import com.tongji.boying.model.Admin;
import com.tongji.boying.model.Role;
import com.tongji.boying.service.UmsAdminService;
import com.tongji.boying.service.UmsMenuService;
import com.tongji.boying.service.UmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台管理员管理
 * 只有拥有该资源权限的管理员能管理后台管理员
 */
@Controller
@Api(tags = "UmsAdminController", description = "后台管理员管理")
@RequestMapping("/admin")
public class UmsAdminController
{
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private UmsRoleService roleService;
    @Autowired
    private UmsMenuService menuService;

    @ApiOperation(value = "管理员注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Admin> register(@Validated @RequestBody UmsAdminRegisterParam param)
    {
        Admin admin = adminService.register(param);
        if (admin == null)
        {
            return CommonResult.failed("注册失败!");
        }
        return CommonResult.success();
    }

    @ApiOperation("根据管理员名分页获取管理员列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<Admin>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum)
    {
//        pageNum为页数，pageSize为一个页面显示几条数据
        List<Admin> adminList = adminService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(adminList));
    }

    @ApiOperation("获取指定管理员信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Map<String, Object>> getItem(@PathVariable Integer id)
    {
        Admin admin = adminService.getItem(id);
        if (admin == null) return CommonResult.failed("查询的管理员不存在");
        Map<String, Object> data = new HashMap<>();
        data.put("admin", admin);
        data.put("menus", menuService.categoryMap(id));
        data.put("resource", roleService.getResourceList(id));
        data.put("roles", adminService.getRoleList(id));
        return CommonResult.success(data);
    }

    @ApiOperation("修改指定管理员信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Integer id, @Validated @RequestBody UmsAdminInfoParam param)
    {
        int count = adminService.update(id, param);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改指定管理员密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePassword(@RequestParam String username,
                                       @RequestParam String newPassword)
    {
        int status = adminService.updatePassword(username,newPassword);
        if (status > 0)
        {
            return CommonResult.success(status);
        }
        else if (status == -1)
        {
            return CommonResult.failed("提交参数不合法");
        }
        else if (status == -2)
        {
            return CommonResult.failed("找不到该管理员");
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除指定管理员信息")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Integer id)
    {
        int count = adminService.delete(id);
        if(count == -1){
            return CommonResult.failed("不能删除自身账号!");
        }
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改帐号状态")
    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateStatus(@PathVariable Integer id, @RequestParam(value = "status") Boolean status)
    {

        UmsAdminInfoParam param = new UmsAdminInfoParam();
        param.setStatus(status);
        int count = adminService.update(id, param);
        if (count > 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("给管理员分配角色")
    @RequestMapping(value = "/role/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateRole(@RequestParam("adminId") Integer adminId,
                                   @RequestParam("roleIds") List<Integer> roleIds)
    {
        int count = adminService.updateRole(adminId, roleIds);
        if (count >= 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除管理员角色")
    @RequestMapping(value = "/role/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteRole(@RequestParam("adminId") Integer adminId,
                                   @RequestParam("roleIds") List<Integer> roleIds)
    {
        int count = adminService.deleteRole(adminId, roleIds);
        if (count >= 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取指定管理员的角色")
    @RequestMapping(value = "/role/{adminId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<Role>> getRoleList(@PathVariable Integer adminId)
    {
        List<Role> roleList = adminService.getRoleList(adminId);
        return CommonResult.success(roleList);
    }
}
