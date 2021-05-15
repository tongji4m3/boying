package com.tongji.boying.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dao.UmsRoleDao;
import com.tongji.boying.dto.UmsMenuParam;
import com.tongji.boying.mapper.AdminMenuMapper;
import com.tongji.boying.model.AdminMenu;
import com.tongji.boying.model.AdminMenuExample;
import com.tongji.boying.service.UmsMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 后台菜单管理Service实现类
 */
@Service
public class UmsMenuServiceImpl implements UmsMenuService
{
    @Autowired
    private AdminMenuMapper AdminMenuMapper;
    @Autowired
    private UmsRoleDao roleDao;

    @Override
    public int create(UmsMenuParam param)
    {
        //检查输入的param是否合法,为了create与update复用
        //默认-1代表是create模式的检查
        checkMenuParam(param, -1);
        //不能自己更新菜单level
        AdminMenu AdminMenu = new AdminMenu();
        BeanUtils.copyProperties(param, AdminMenu);
        AdminMenu.setCreateTime(new Date());
//        AdminMenu.setStatus(true);
        updateLevel(AdminMenu);
        return AdminMenuMapper.insertSelective(AdminMenu);
    }

    private void checkMenuParam(UmsMenuParam param, Integer id)
    {
        //        检查是否有重名菜单
        AdminMenuExample AdminMenuExample = new AdminMenuExample();
        AdminMenuExample.Criteria criteria = AdminMenuExample.createCriteria();
        criteria.andTitleEqualTo(param.getTitle());
        //为了修改时不会连本身的菜单都不能修改
        if (id != -1)
        {
            criteria.andIdNotEqualTo(id);
        }
        List<AdminMenu> menus = AdminMenuMapper.selectByExample(AdminMenuExample);
        if (CollUtil.isNotEmpty(menus))
        {
            Asserts.fail("菜单名称重复!");
        }
        //检查parentId
        if (param.getParentId() != 0)
        {
            //要插入的父级菜单存在
            AdminMenuExample menuExample1 = new AdminMenuExample();
            menuExample1.createCriteria().andIdEqualTo(param.getParentId()).andParentIdEqualTo(0);
            List<AdminMenu> menus1 = AdminMenuMapper.selectByExample(menuExample1);
            if (CollUtil.isEmpty(menus1))
            {
                Asserts.fail("输入的parentId不合法!");
            }
        }
    }

    /**
     * 修改菜单层级
     */
    private void updateLevel(AdminMenu AdminMenu)
    {
        if (AdminMenu.getParentId() == 0)
        {
            //没有父菜单时为一级菜单
            AdminMenu.setLevel(0);
        }
        else
        {
            //有父菜单时选择根据父菜单level设置
            AdminMenu parentMenu = AdminMenuMapper.selectByPrimaryKey(AdminMenu.getParentId());
            if (parentMenu != null)
            {
                AdminMenu.setLevel(parentMenu.getLevel() + 1);
            }
            else
            {
                AdminMenu.setLevel(0);
            }
        }
    }

    @Override
    public int update(Integer id, UmsMenuParam param)
    {
        //如果是修改首先检查对应修改id是否存在
        // 还需要检查是否是一级菜单,一级菜单不能再修改parentId
        AdminMenu checkMenu = AdminMenuMapper.selectByPrimaryKey(id);
        if (checkMenu == null)
        {
            Asserts.fail("要修改的菜单id不存在!");
        }
        if (checkMenu.getParentId() == 0 && param.getParentId() != 0)
        {
            //说明是父级菜单 不能再修改parentId
            //且传入参数有误
            Asserts.fail("不能修改一级菜单的parentId!");
        }

        checkMenuParam(param, id);

        //不能自己更新菜单level
        AdminMenu AdminMenu = new AdminMenu();
        BeanUtils.copyProperties(param, AdminMenu);
        AdminMenu.setId(id);
        updateLevel(AdminMenu);
        return AdminMenuMapper.updateByPrimaryKeySelective(AdminMenu);
    }

    @Override
    public AdminMenu getItem(Integer id)
    {
        return AdminMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(Integer id)
    {
        AdminMenu AdminMenu = getItem(id);
        if (AdminMenu == null)
        {
            Asserts.fail("要删除的菜单不存在!");
        }
        if (AdminMenu.getParentId() == 0)
        {
            //说明是父级菜单
            AdminMenuExample example = new AdminMenuExample();
            example.createCriteria().andParentIdEqualTo(AdminMenu.getId());
            if (ObjectUtil.isNotEmpty(AdminMenuMapper.selectByExample(example)))
            {
                //说明有子菜单
                Asserts.fail("该菜单还有子菜单,不能删除!");
            }
        }
        return AdminMenuMapper.deleteByPrimaryKey(id);
    }


    @Override
    public List<AdminMenu> list(Integer parentId, Integer pageSize, Integer pageNum)
    {
        PageHelper.startPage(pageNum, pageSize);
        AdminMenuExample example = new AdminMenuExample();
        example.setOrderByClause("weight desc");
        example.createCriteria().andParentIdEqualTo(parentId);
        return AdminMenuMapper.selectByExample(example);
    }

    @Override
    public Map<AdminMenu, List<AdminMenu>> categoryMap()
    {
        //用LinkedHashMap保持插入顺序,保证最后结果的权重
        Map<AdminMenu, List<AdminMenu>> map = new LinkedHashMap<>();
        AdminMenuExample example = new AdminMenuExample();
        example.createCriteria().andParentIdEqualTo(0);
        example.setOrderByClause("weight desc");
        List<AdminMenu> parents = AdminMenuMapper.selectByExample(example);
        for (AdminMenu parent : parents)
        {
            AdminMenuExample AdminMenuExample = new AdminMenuExample();
            AdminMenuExample.setOrderByClause("weight desc");
            AdminMenuExample.createCriteria().andParentIdEqualTo(parent.getId());
            map.put(parent, AdminMenuMapper.selectByExample(AdminMenuExample));
        }
        return map;
    }

    @Override
    public Map<AdminMenu, List<AdminMenu>> categoryMap(Integer adminId)
    {
        //用LinkedHashMap保持插入顺序,保证最后结果的权重
        Map<AdminMenu, List<AdminMenu>> map = new LinkedHashMap<>();

        //管理员Id对应的所有菜单信息,其中role必须有效
        List<AdminMenu> menuList = roleDao.getMenuList(adminId);
        //先获取父级可用菜单 并排序
        List<AdminMenu> parents = menuList.stream().
                filter(AdminMenu -> AdminMenu.getParentId() == 0 && AdminMenu.getStatus())
                .sorted((menu1,menu2)->{return menu2.getWeight()-menu1.getWeight();}).
        collect(Collectors.toList());

        //对每个父级菜单
        for (AdminMenu parent : parents)
        {
            //获取该父级菜单的子菜单
            List<AdminMenu> sons = menuList.stream().
                    filter(AdminMenu -> AdminMenu.getParentId().equals(parent.getId()) && AdminMenu.getStatus())
                    .sorted((menu1,menu2)->{return menu2.getWeight()-menu1.getWeight();}).
                            collect(Collectors.toList());
            map.put(parent, sons);
        }
        return map;
    }


    @Override
    public int updateHidden(Integer id, Boolean hidden)
    {
        AdminMenu AdminMenu = new AdminMenu();
        AdminMenu.setId(id);
        AdminMenu.setStatus(hidden);
        return AdminMenuMapper.updateByPrimaryKeySelective(AdminMenu);
    }

}
