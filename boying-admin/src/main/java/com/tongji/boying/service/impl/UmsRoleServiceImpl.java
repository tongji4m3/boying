package com.tongji.boying.service.impl;

import com.github.pagehelper.PageHelper;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.RoleParam;
import com.tongji.boying.mapper.*;
import com.tongji.boying.model.*;
import com.tongji.boying.service.UmsAdminCacheService;
import com.tongji.boying.service.UmsRoleService;
import com.tongji.boying.dao.UmsRoleDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private UmsRoleDao roleDao;

    @Override
    public int create(RoleParam param) {
        Role role = new Role();
        BeanUtils.copyProperties(param, role);
        role.setCreateTime(new Date());
        role.setAdminCount(0);
        role.setStatus(true);
        return roleMapper.insert(role);
    }

    @Override
    public int update(Integer id, RoleParam param) {
        if(!hasRole(id)) Asserts.fail("该角色不存在");
        Role role = new Role();
        BeanUtils.copyProperties(param, role);
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
        if(!hasRole(roleId)) Asserts.fail("该角色不存在");
        return roleDao.getMenuListByRoleId(roleId);
    }

    @Override
    public List<Resource> listResource(Integer roleId) {
        if(!hasRole(roleId)) Asserts.fail("该角色不存在");
        return roleDao.getResourceListByRoleId(roleId);
    }

    @Override
    public int allocMenu(Integer roleId, List<Integer> menuIds) {
        if(!hasRole(roleId)) Asserts.fail("该角色不存在!");
        //先删除原有关系
        RoleMenuExample example=new RoleMenuExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        roleMenuMapper.deleteByExample(example);

        MenuExample menuExample = new MenuExample();
        menuExample.createCriteria().andMenuIdIn(menuIds);
        List<Menu> menus = menuMapper.selectByExample(menuExample);
        if(menus.size()!=menuIds.size())
        {
//            说明有些menuId不存在
            Asserts.fail("列表中某些menuId不存在!");
        }

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
        if(!hasRole(roleId)) Asserts.fail("该角色不存在");
        //先删除原有关系
        RoleResourceExample example=new RoleResourceExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        roleResourceRelationMapper.deleteByExample(example);

        //不在Resource表中则不加
        ResourceExample resourceExample = new ResourceExample();
        resourceExample.createCriteria().andResourceIdIn(resourceIds);
        List<Resource> resources = resourceMapper.selectByExample(resourceExample);
        if(resources.size()!=resourceIds.size())
        {
            //            说明有些resourceId不存在
            Asserts.fail("列表中某些resourceId不存在!");
        }

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

    @Override
    public boolean hasRole(Integer roleId)
    {
        //先判断角色是否存在
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andRoleIdEqualTo(roleId);
        List<Role> roles = roleMapper.selectByExample(roleExample);
        return !roles.isEmpty();
    }
}
