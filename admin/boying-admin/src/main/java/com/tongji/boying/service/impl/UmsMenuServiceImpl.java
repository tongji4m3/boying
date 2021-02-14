package com.tongji.boying.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dao.UmsRoleDao;
import com.tongji.boying.dto.UmsMenuParam;
import com.tongji.boying.mapper.MenuMapper;
import com.tongji.boying.model.Menu;
import com.tongji.boying.model.MenuExample;
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
    private MenuMapper menuMapper;
    @Autowired
    private UmsRoleDao roleDao;

    @Override
    public int create(UmsMenuParam param)
    {
        //检查输入的param是否合法,为了create与update复用
        //默认-1代表是create模式的检查
        checkMenuParam(param, -1);
        //不能自己更新菜单level
        Menu menu = new Menu();
        BeanUtils.copyProperties(param, menu);
        menu.setCreateTime(new Date());
        menu.setHidden(false);
        updateLevel(menu);
        return menuMapper.insertSelective(menu);
    }

    private void checkMenuParam(UmsMenuParam param, Integer id)
    {
        //        检查是否有重名菜单
        MenuExample menuExample = new MenuExample();
        MenuExample.Criteria criteria = menuExample.createCriteria();
        criteria.andTitleEqualTo(param.getTitle());
        //为了修改时不会连本身的菜单都不能修改
        if (id != -1)
        {
            criteria.andMenuIdNotEqualTo(id);
        }
        List<Menu> menus = menuMapper.selectByExample(menuExample);
        if (CollUtil.isNotEmpty(menus))
        {
            Asserts.fail("菜单名称重复!");
        }
        //检查parentId
        if (param.getParentId() != 0)
        {
            //要插入的父级菜单存在
            MenuExample menuExample1 = new MenuExample();
            menuExample1.createCriteria().andMenuIdEqualTo(param.getParentId()).andParentIdEqualTo(0);
            List<Menu> menus1 = menuMapper.selectByExample(menuExample1);
            if (CollUtil.isEmpty(menus1))
            {
                Asserts.fail("输入的parentId不合法!");
            }
        }
    }

    /**
     * 修改菜单层级
     */
    private void updateLevel(Menu menu)
    {
        if (menu.getParentId() == 0)
        {
            //没有父菜单时为一级菜单
            menu.setLevel(0);
        }
        else
        {
            //有父菜单时选择根据父菜单level设置
            Menu parentMenu = menuMapper.selectByPrimaryKey(menu.getParentId());
            if (parentMenu != null)
            {
                menu.setLevel(parentMenu.getLevel() + 1);
            }
            else
            {
                menu.setLevel(0);
            }
        }
    }

    @Override
    public int update(Integer id, UmsMenuParam param)
    {
        //如果是修改首先检查对应修改id是否存在
        // 还需要检查是否是一级菜单,一级菜单不能再修改parentId
        Menu checkMenu = menuMapper.selectByPrimaryKey(id);
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
        Menu menu = new Menu();
        BeanUtils.copyProperties(param, menu);
        menu.setMenuId(id);
        updateLevel(menu);
        return menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public Menu getItem(Integer id)
    {
        return menuMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(Integer id)
    {
        Menu menu = getItem(id);
        if (menu == null)
        {
            Asserts.fail("要删除的菜单不存在!");
        }
        if (menu.getParentId() == 0)
        {
            //说明是父级菜单
            MenuExample example = new MenuExample();
            example.createCriteria().andParentIdEqualTo(menu.getMenuId());
            if (ObjectUtil.isNotEmpty(menuMapper.selectByExample(example)))
            {
                //说明有子菜单
                Asserts.fail("该菜单还有子菜单,不能删除!");
            }
        }
        return menuMapper.deleteByPrimaryKey(id);
    }


    @Override
    public List<Menu> list(Integer parentId, Integer pageSize, Integer pageNum)
    {
        PageHelper.startPage(pageNum, pageSize);
        MenuExample example = new MenuExample();
        example.setOrderByClause("sort desc");
        example.createCriteria().andParentIdEqualTo(parentId);
        return menuMapper.selectByExample(example);
    }

    @Override
    public Map<Menu, List<Menu>> categoryMap()
    {
        //用LinkedHashMap保持插入顺序,保证最后结果的权重
        Map<Menu, List<Menu>> map = new LinkedHashMap<>();
        MenuExample example = new MenuExample();
        example.createCriteria().andParentIdEqualTo(0);
        example.setOrderByClause("sort desc");
        List<Menu> parents = menuMapper.selectByExample(example);
        for (Menu parent : parents)
        {
            MenuExample menuExample = new MenuExample();
            menuExample.setOrderByClause("sort desc");
            menuExample.createCriteria().andParentIdEqualTo(parent.getMenuId());
            map.put(parent, menuMapper.selectByExample(menuExample));
        }
        return map;
    }

    @Override
    public Map<Menu, List<Menu>> categoryMap(Integer adminId)
    {
        //用LinkedHashMap保持插入顺序,保证最后结果的权重
        Map<Menu, List<Menu>> map = new LinkedHashMap<>();

        //管理员Id对应的所有菜单信息,其中role必须有效
        List<Menu> menuList = roleDao.getMenuList(adminId);
        //先获取父级可用菜单 并排序
        List<Menu> parents = menuList.stream().
                filter(menu -> menu.getParentId() == 0 && !menu.getHidden() && menu.getSort() != 0)
                .sorted((menu1,menu2)->{return menu2.getSort()-menu1.getSort();}).
        collect(Collectors.toList());

        //对每个父级菜单
        for (Menu parent : parents)
        {
            //获取该父级菜单的子菜单
            List<Menu> sons = menuList.stream().
                    filter(menu -> menu.getParentId().equals(parent.getMenuId()) && !menu.getHidden() && menu.getSort() != 0)
                    .sorted((menu1,menu2)->{return menu2.getSort()-menu1.getSort();}).
                            collect(Collectors.toList());
            map.put(parent, sons);
        }
        return map;
    }


    @Override
    public int updateHidden(Integer id, Boolean hidden)
    {
        Menu menu = new Menu();
        menu.setMenuId(id);
        menu.setHidden(hidden);
        return menuMapper.updateByPrimaryKeySelective(menu);
    }

}
