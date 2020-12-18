package com.tongji.boying.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.mapper.BoyingShowMapper;
import com.tongji.boying.model.BoyingShow;
import com.tongji.boying.model.BoyingShowExample;
import com.tongji.boying.model.Category;
import com.tongji.boying.service.ShowCategoryService;
import com.tongji.boying.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowServiceImpl implements ShowService {
    @Autowired
    private BoyingShowMapper showMapper;
    @Autowired
    private ShowCategoryService showCategoryService;

    @Override
    public List<BoyingShow> search(String keyword, String city, Integer categoryId, Date date, Integer pageNum, Integer pageSize, Integer sort) {
        PageHelper.startPage(pageNum, pageSize);//分页相关
        BoyingShowExample example = new BoyingShowExample();
        BoyingShowExample.Criteria criteria = example.createCriteria();
        //关键词模糊搜索
        if (StrUtil.isNotEmpty(keyword)) {
            criteria.andNameLike("%" + keyword + "%");
        }
        //按城市搜索 全国则不限制
        if (StrUtil.isNotEmpty(city) && !city.equals("全国")) {
            criteria.andCityEqualTo(city);
        }
        //目录搜索
        if (categoryId != null && categoryId != -1 && categoryId != 0) {
            //说明是子目录,可以直接查
            if (showCategoryService.isSonCategory(categoryId)) {
                criteria.andCategoryIdEqualTo(categoryId);
            }
            else {
                List<Category> categories = showCategoryService.categoryList(categoryId);

                //show中的分类都是二级分类,所以如果只是直接搜索一级分类的,就需要先把一级分类的所有二级分类先查询
                List<Integer> collect = categories.stream()
                        .map(Category::getCategoryId)
                        .collect(Collectors.toList());
                System.out.println(collect);
                criteria.andCategoryIdIn(collect);
            }
        }
        //按时间搜索
        if (date != null) {
//            查询的时间在开始时间,结束时间之间
            criteria.andDayStartLessThanOrEqualTo(date).andDayEndGreaterThanOrEqualTo(date);
        }
        //0->按相关度；1->按推荐；2->按时间；3->最低价格从低到高；4->最低价格从高到低
        if (sort == 1) {
            example.setOrderByClause("weight desc");
        }
        else if (sort == 2) {
            example.setOrderByClause("day_start desc");
        }
        else if (sort == 3) {
            example.setOrderByClause("min_price asc");
        }
        else if (sort == 4) {
            example.setOrderByClause("min_price desc");
        }
        List<BoyingShow> boyingShows = null;
        try {
           boyingShows  = showMapper.selectByExample(example);
        }
        catch (Exception e){
            Asserts.fail("查询不到信息,参看参数是否有误!");
        }
        return boyingShows;
    }

    @Override
    public BoyingShow detail(int id) {
        return showMapper.selectByPrimaryKey(id);
    }
}
