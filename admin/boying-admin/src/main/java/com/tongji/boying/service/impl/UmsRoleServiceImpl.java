package com.tongji.boying.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.tongji.boying.common.api.CommonResult;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dao.UmsAdminRoleDao;
import com.tongji.boying.dao.UmsRoleDao;
import com.tongji.boying.dto.UmsRoleParam;
import com.tongji.boying.mapper.*;
import com.tongji.boying.model.*;
import com.tongji.boying.service.UmsAdminCacheService;
import com.tongji.boying.service.UmsRoleService;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * 后台角色管理Service实现类
 */
@Service
public class UmsRoleServiceImpl implements UmsRoleService
{
    @Autowired
    private AdminRoleMapper AdminRoleMapper;
    @Autowired
    private AdminRoleMenuMapper AdminRoleMenuMapper;
    @Autowired
    private AdminRoleResourceMapper roleResourceRelationMapper;
    @Autowired
    private UmsAdminCacheService adminCacheService;

    @Autowired
    private AdminMenuMapper AdminMenuMapper;

    @Autowired
    private AdminResourceMapper AdminResourceMapper;

    @Autowired
    private UmsRoleDao roleDao;
    @Autowired
    private UmsAdminRoleDao adminRoleDao;

    @Override
    public int create(UmsRoleParam param)
    {
        checkRoleParam(param, -1);
        AdminRole AdminRole = new AdminRole();
        BeanUtils.copyProperties(param, AdminRole);
        AdminRole.setCreateTime(new Date());
        AdminRole.setAdminCount(0);
//        AdminRole.setStatus(true);
        return AdminRoleMapper.insertSelective(AdminRole);
    }

    private void checkRoleParam(UmsRoleParam param, Integer id)
    {
        AdminRoleExample AdminRoleExample = new AdminRoleExample();
        AdminRoleExample.Criteria criteria = AdminRoleExample.createCriteria();
        criteria.andNameEqualTo(param.getName());
        if (id != -1)
        {
            criteria.andIdNotEqualTo(id);
        }
        List<AdminRole> roles = AdminRoleMapper.selectByExample(AdminRoleExample);
        if (CollUtil.isNotEmpty(roles))
        {
            Asserts.fail("角色名称不能重复!");
        }
    }


    @Override
    public int update(Integer id, UmsRoleParam param)
    {
        checkRoleParam(param, id);
        AdminRole AdminRole = new AdminRole();
        BeanUtils.copyProperties(param, AdminRole);
        AdminRole.setId(id);
        return AdminRoleMapper.updateByPrimaryKeySelective(AdminRole);
    }

    @Override
    public int delete(List<Integer> ids)
    {
        AdminRoleExample example = new AdminRoleExample();
        example.createCriteria().andIdIn(ids);
        if (AdminRoleMapper.selectByExample(example).size() != ids.size())
        {
            Asserts.fail("某些角色Id不存在!");
        }
        int count = AdminRoleMapper.deleteByExample(example);
        adminCacheService.delResourceListByRoleIds(ids);
        return count;
    }

    @Override
    public int delete(Integer id)
    {
        int count = AdminRoleMapper.deleteByPrimaryKey(id);
        adminCacheService.delResourceListByRole(id);
        return count;
    }


    @Override
    public List<AdminRole> list()
    {
        return AdminRoleMapper.selectByExample(new AdminRoleExample());
    }

    @Override
    public List<AdminRole> list(String keyword, Integer pageSize, Integer pageNum)
    {
        PageHelper.startPage(pageNum, pageSize);
        AdminRoleExample example = new AdminRoleExample();
        if (!StringUtils.isEmpty(keyword))
        {
            example.createCriteria().andNameLike("%" + keyword + "%");
        }
        return AdminRoleMapper.selectByExample(example);
    }

    @Override
    public List<AdminMenu> getMenuList(Integer adminId)
    {

        return roleDao.getMenuList(adminId);
    }

    @Override
    public List<AdminMenu> listMenu(Integer roleId)
    {
        if (ObjectUtil.isEmpty(AdminRoleMapper.selectByPrimaryKey(roleId))) Asserts.fail("该角色不存在");
        return roleDao.getMenuListByRoleId(roleId);
    }

    @Override
    public List<AdminResource> listResource(Integer roleId)
    {
        if (ObjectUtil.isEmpty(AdminRoleMapper.selectByPrimaryKey(roleId))) Asserts.fail("该角色不存在");
        return roleDao.getResourceListByRoleId(roleId);
    }

    @Override
    public int allocMenu(Integer roleId, List<Integer> menuIds)
    {
        if (ObjectUtil.isEmpty(AdminRoleMapper.selectByPrimaryKey(roleId))) Asserts.fail("该角色不存在");
        //先删除原有关系
        AdminRoleMenuExample example = new AdminRoleMenuExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        AdminRoleMenuMapper.deleteByExample(example);
        if (menuIds.size() == 0)
        {
            return 0;
        }
        AdminMenuExample AdminMenuExample = new AdminMenuExample();
        AdminMenuExample.createCriteria().andIdIn(menuIds);
        List<AdminMenu> menus = AdminMenuMapper.selectByExample(AdminMenuExample);
        if (menus.size() != menuIds.size())
        {
//            说明有些menuId不存在
            Asserts.fail("列表中某些menuId不存在!");
        }
        System.out.println(menuIds);
        //批量插入新关系

        LinkedHashSet<Integer> set = new LinkedHashSet<Integer>();
        List<Integer> parentList = new ArrayList<>();

        for (Integer menuId : menuIds)
        {
            System.out.println("a");
            System.out.println(menuId);
            AdminMenu adminMenu = AdminMenuMapper.selectByPrimaryKey(menuId);
            //这里不添加一级菜单
            if (adminMenu.getParentId() != 0)
            {
                parentList.add(adminMenu.getParentId());
                AdminRoleMenu relation = new AdminRoleMenu();
                relation.setRoleId(roleId);
                relation.setMenuId(menuId);
                AdminRoleMenuMapper.insert(relation);
            }

        }
        set.addAll(parentList);
        parentList.clear();
        parentList.addAll(set);
        for (Integer menuId : parentList)
        {
            System.out.println("b");
            System.out.println(menuId);
            AdminRoleMenu relation = new AdminRoleMenu();
            relation.setRoleId(roleId);
            relation.setMenuId(menuId);
            AdminRoleMenuMapper.insert(relation);
        }
        return menuIds.size() + parentList.size();
    }

    @Override
    public int allocResource(Integer roleId, List<Integer> resourceIds)
    {
        if (ObjectUtil.isEmpty(AdminRoleMapper.selectByPrimaryKey(roleId))) Asserts.fail("该角色不存在");
        //先删除原有关系
        AdminRoleResourceExample example = new AdminRoleResourceExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        roleResourceRelationMapper.deleteByExample(example);

        //不在Resource表中则不加
        AdminResourceExample AdminResourceExample = new AdminResourceExample();
        AdminResourceExample.createCriteria().andIdIn(resourceIds);
        List<AdminResource> resources = AdminResourceMapper.selectByExample(AdminResourceExample);
        if (resources.size() != resourceIds.size())
        {
            //            说明有些resourceId不存在
            Asserts.fail("列表中某些resourceId不存在!");
        }

        //批量插入新关系
        for (Integer resourceId : resourceIds)
        {
            AdminRoleResource relation = new AdminRoleResource();
            relation.setRoleId(roleId);
            relation.setResourceId(resourceId);
            roleResourceRelationMapper.insert(relation);
        }
        adminCacheService.delResourceListByRole(roleId);
        return resourceIds.size();
    }

    @Override
    public List<AdminResource> getResourceList(Integer adminId)
    {
        List<AdminResource> resourceList = adminCacheService.getResourceList(adminId);
        if (CollUtil.isNotEmpty(resourceList))
        {
            return resourceList;
        }
        resourceList = adminRoleDao.getResourceList(adminId);
        if (CollUtil.isNotEmpty(resourceList))
        {
            adminCacheService.setResourceList(adminId, resourceList);
        }
        return resourceList;
    }

    @Override
    public int updateStatus(Integer id, Boolean status)
    {
        AdminRole AdminRole = new AdminRole();
        AdminRole.setStatus(status);
        AdminRole.setId(id);
        //因为角色启用状态改变,所以删除与该角色相关的管理员的资源缓存
        adminCacheService.delResourceListByRole(id);
        return AdminRoleMapper.updateByPrimaryKeySelective(AdminRole);
    }
}
