package com.tongji.boying.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.tongji.boying.mapper.ShowMapper;
import com.tongji.boying.model.Category;
import com.tongji.boying.model.Show;
import com.tongji.boying.model.ShowExample;
import com.tongji.boying.service.CategoryService;
import com.tongji.boying.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class ShowServiceImpl implements ShowService
{
    @Autowired
    private ShowMapper showMapper;
    @Autowired
    private CategoryService categoryService;

    @Override
    public List<Show> search(String keyword, String city, Integer categoryId, Date date, Integer pageNum, Integer pageSize, Integer sort)
    {
        PageHelper.startPage(pageNum, pageSize);//分页相关
        ShowExample example = new ShowExample();
        ShowExample.Criteria criteria = example.createCriteria();
        //关键词模糊搜索
        if (StrUtil.isNotEmpty(keyword))
        {
            criteria.andNameLike("%" + keyword + "%");
        }
        //按城市搜索
        if (StrUtil.isNotEmpty(city))
        {
            criteria.andCityEqualTo(city);
        }
        //目录搜索
        if (categoryId != null)
        {
            //说明是子目录,可以直接查
            if(categoryService.isSonCategory(categoryId))
            {
                criteria.andCategoryIdEqualTo(categoryId);
            }
            else
            {
                //show中的分类都是二级分类,所以如果只是直接搜索一级分类的,就需要先把一级分类的所有二级分类先查询
                List<Category> categories = categoryService.categoryList(categoryId);
                List<Integer> categoriesId = new LinkedList<>();
                for (Category category : categories)
                {
                    categoriesId.add(category.getCategoryId());
                }
                criteria.andCategoryIdIn(categoriesId);
            }
        }
        //按时间搜索
        if(date!=null)
        {
//            查询的时间在开始时间,结束时间之间
            criteria.andDayStartLessThanOrEqualTo(date).andDayEndGreaterThanOrEqualTo(date);
        }
        //0->按相关度；1->按推荐；2->按时间；3->最低价格从低到高；4->最低价格从高到低
        if (sort == 1)
        {
            example.setOrderByClause("id desc");
        }
        else if (sort == 2)
        {
            example.setOrderByClause("day_start desc");
        }
        else if (sort == 3)
        {
            example.setOrderByClause("min_price asc");
        }
        else if (sort == 4)
        {
            example.setOrderByClause("min_price desc");
        }
        return showMapper.selectByExample(example);
    }

    @Override
    public Show detail(int id)
    {
        return showMapper.selectByPrimaryKey(id);
    }
}
