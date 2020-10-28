package com.tongji.boying.service.impl;

import com.github.pagehelper.PageHelper;
import com.tongji.boying.mapper.RoleMapper;
import com.tongji.boying.mapper.RoleMenuMapper;
import com.tongji.boying.mapper.RoleResourceMapper;
import com.tongji.boying.model.*;
import com.tongji.boying.service.UmsAdminCacheService;
import com.tongji.boying.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 后台角色管理Service实现类
 */
@Service
public class UmsRoleServiceImpl implements UmsRoleService
{
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private RoleResourceMapper roleResourceRelationMapper;
    @Autowired
    private UmsAdminCacheService adminCacheService;
    @Override
    public int create(Role role) {
        role.setCreateTime(new Date());
        role.setAdminCount(0);
        role.setSort(0);
        return roleMapper.insert(role);
    }

    @Override
    public int update(Integer id, Role role) {
        role.setRoleId(id);
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public int delete(List<Integer> ids) {
        RoleExample example = new RoleExample();
        example.createCriteria().andRoleIdIn(ids);
        int count = roleMapper.deleteByExample(example);
        adminCacheService.delResourceListByRoleIds(ids);
        return count;
    }



    @Override
    public List<Role> list() {
        return roleMapper.selectByExample(new RoleExample());
    }

    @Override
    public List<Role> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        RoleExample example = new RoleExample();
        if (!StringUtils.isEmpty(keyword)) {
            example.createCriteria().andNameLike("%" + keyword + "%");
        }
        return roleMapper.selectByExample(example);
    }

    @Override
    public List<Menu> getMenuList(Integer adminId) {
        return roleDao.getMenuList(adminId);
    }

    @Override
    public List<Menu> listMenu(Integer roleId) {
        return roleDao.getMenuListByRoleId(roleId);
    }

    @Override
    public List<Resource> listResource(Integer roleId) {
        return roleDao.getResourceListByRoleId(roleId);
    }

    @Override
    public int allocMenu(Integer roleId, List<Integer> menuIds) {
        //先删除原有关系
        RoleMenuExample example=new RoleMenuExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        roleMenuMapper.deleteByExample(example);
        //批量插入新关系
        for (Integer menuId : menuIds) {
            RoleMenu relation = new RoleMenu();
            relation.setRoleId(roleId);
            relation.setMenuId(menuId);
            roleMenuMapper.insert(relation);
        }
        return menuIds.size();
    }

    @Override
    public int allocResource(Integer roleId, List<Integer> resourceIds) {
        //先删除原有关系
        RoleResourceExample example=new RoleResourceExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        roleResourceRelationMapper.deleteByExample(example);
        //批量插入新关系
        for (Integer resourceId : resourceIds) {
            RoleResource relation = new RoleResource();
            relation.setRoleId(roleId);
            relation.setResourceId(resourceId);
            roleResourceRelationMapper.insert(relation);
        }
        adminCacheService.delResourceListByRole(roleId);
        return resourceIds.size();
    }
}
