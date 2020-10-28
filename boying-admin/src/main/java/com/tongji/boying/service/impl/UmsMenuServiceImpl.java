package com.tongji.boying.service.impl;

import com.github.pagehelper.PageHelper;
import com.tongji.boying.mapper.MenuMapper;
import com.tongji.boying.model.Category;
import com.tongji.boying.model.Menu;
import com.tongji.boying.model.MenuExample;
import com.tongji.boying.service.UmsMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 后台菜单管理Service实现类
 */
@Service
public class UmsMenuServiceImpl implements UmsMenuService
{
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public int create(Menu menu) {
        menu.setCreateTime(new Date());
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
    public int update(Integer id, Menu menu) {
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
    public Map<Category, List<Category>> categoryMap()
    {
        List<Menu> menuList = menuMapper.selectByExample(new UmsMenuExample());
        Map<Category, List<Category>> result = menuList.stream()
                .filter(menu -> menu.getParentId().equals(0L))
                .map(menu -> covertMenuNode(menu, menuList)).collect(Collectors.toList());
        return result;
    }


    @Override
    public int updateHidden(Integer id, Boolean hidden) {
        Menu menu = new Menu();
        menu.setMenuId(id);
        menu.setHidden(hidden);
        return menuMapper.updateByPrimaryKeySelective(menu);
    }

}
