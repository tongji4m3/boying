package com.tongji.boying.service.impl;

import com.github.pagehelper.PageHelper;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.MenuParam;
import com.tongji.boying.mapper.MenuMapper;
import com.tongji.boying.model.Menu;
import com.tongji.boying.model.MenuExample;
import com.tongji.boying.service.UmsMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台菜单管理Service实现类
 */
@Service
public class UmsMenuServiceImpl implements UmsMenuService
{
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public int create(MenuParam param) {
        //不能自己更新菜单level
        Menu menu = new Menu();
        menu.setCreateTime(new Date());
        menu.setHidden(param.getHidden());
        menu.setIcon(param.getIcon());
        menu.setParentId(param.getParentId());
        menu.setSort(param.getSort());
        menu.setTitle(param.getTitle());
        updateLevel(menu);
        return menuMapper.insert(menu);
    }

    /**
     * 修改菜单层级
     */
    private void updateLevel(Menu menu) {
        if (menu.getParentId() == 0) {
            //没有父菜单时为一级菜单
            menu.setLevel(0);
        } else {
            //有父菜单时选择根据父菜单level设置
            Menu parentMenu = menuMapper.selectByPrimaryKey(menu.getParentId());
            if (parentMenu != null) {
                menu.setLevel(parentMenu.getLevel() + 1);
            } else {
                menu.setLevel(0);
            }
        }
    }

    @Override
    public int update(Integer id, MenuParam param) {
        //不能自己更新菜单level
        Menu menu = new Menu();
        menu.setHidden(param.getHidden());
        menu.setIcon(param.getIcon());
        menu.setParentId(param.getParentId());
        menu.setSort(param.getSort());
        menu.setTitle(param.getTitle());
        menu.setMenuId(id);
        updateLevel(menu);
        return menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public Menu getItem(Integer id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(Integer id) {
        Menu menu = getItem(id);
        if(menu==null)
        {
            Asserts.fail("要删除的菜单不存在!");
        }
        System.out.println(menu);
        if(menu.getParentId()==0)
        {
            //说明是父级菜单
            MenuExample example = new MenuExample();
            example.createCriteria().andParentIdEqualTo(menu.getMenuId());
            if(!menuMapper.selectByExample(example).isEmpty())
            {
                //说明有子菜单
                Asserts.fail("该菜单还有子菜单,不能删除!");
            }
        }
        return menuMapper.deleteByPrimaryKey(id);
    }



    @Override
    public List<Menu> list(Integer parentId, Integer pageSize, Integer pageNum) {
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
    public int updateHidden(Integer id, Boolean hidden) {
        Menu menu = new Menu();
        menu.setMenuId(id);
        menu.setHidden(hidden);
        return menuMapper.updateByPrimaryKeySelective(menu);
    }

}
