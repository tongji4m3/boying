package com.tongji.boying.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.showParam.ShowParam;
import com.tongji.boying.mapper.BoyingShowMapper;
import com.tongji.boying.model.BoyingShow;
import com.tongji.boying.model.BoyingShowExample;
import com.tongji.boying.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowServiceImpl implements ShowService {
    @Autowired
    private BoyingShowMapper showMapper;

    @Override
    public List<BoyingShow> search(ShowParam param) {
        BoyingShowExample example = new BoyingShowExample();
        BoyingShowExample.Criteria criteria = example.createCriteria();

        //关键词模糊搜索
        if (StrUtil.isNotEmpty(param.getKeyword())) {
            criteria.andNameLike("%" + param.getKeyword() + "%");
        }

        //按城市搜索 全国则不限制
        if (StrUtil.isNotEmpty(param.getCity()) && !param.getCity().equals("全国")) {
            criteria.andCityEqualTo(param.getCity());
        }

        //根据菜单搜索
        if (param.getCategoryId() != null && param.getCategoryId() != 0) {
            criteria.andCategoryIdEqualTo(param.getCategoryId());
        }

        example.setOrderByClause("weight desc");

        Integer pageNum = param.getPageNum();
        if (pageNum == null || pageNum == 0) pageNum = 0;
        Integer pageSize = param.getPageSize();
        if (pageSize == null || pageSize == 0) pageSize = 5;
        
        PageHelper.startPage(pageNum, pageSize);
        List<BoyingShow> boyingShows = showMapper.selectByExample(example);
        if (boyingShows == null || boyingShows.size() == 0) {
            Asserts.fail("查询不到演出信息!");
        }
        return boyingShows;
    }

    @Override
    public BoyingShow detail(int id) {
        BoyingShow boyingShow = showMapper.selectByPrimaryKey(id);
        if (boyingShow == null) Asserts.fail("演出信息不存在！");
        return boyingShow;
    }
}
