package com.tongji.boying.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.showParam.BoyingShowReturn;
import com.tongji.boying.dto.showParam.SmsShowListParam;
import com.tongji.boying.dto.showParam.SmsShowParam;
import com.tongji.boying.mapper.BoyingCategoryMapper;
import com.tongji.boying.mapper.BoyingShowMapper;
import com.tongji.boying.model.BoyingCategory;
import com.tongji.boying.model.BoyingShow;
import com.tongji.boying.model.BoyingShowExample;
import com.tongji.boying.service.SmsShowService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * 后台演出管理Service实现类
 */
@Service
public class SmsShowServiceImpl implements SmsShowService {
    @Autowired
    private BoyingShowMapper boyingShowMapper;
    @Autowired
    private BoyingCategoryMapper boyingCategoryMapper;

    @Override
    public List<BoyingShowReturn> list(SmsShowListParam param) {
        BoyingShowExample example = new BoyingShowExample();
        BoyingShowExample.Criteria criteria = example.createCriteria();

        //关键词模糊搜索
        if (StrUtil.isNotEmpty(param.getName())) {
            criteria.andNameLike("%" + param.getName() + "%");
        }

        //根据菜单搜索
        if (param.getCategoryId() != null && param.getCategoryId() != 0) {
            criteria.andCategoryIdEqualTo(param.getCategoryId());
        }

        example.setOrderByClause("weight desc");

        Integer pageNum = param.getPageNum();
        if (pageNum == null || pageNum == 0) pageNum = 1;
        Integer pageSize = param.getPageSize();
        if (pageSize == null || pageSize == 0) pageSize = 5;

        PageHelper.startPage(pageNum, pageSize);
        List<BoyingShow> boyingShows = boyingShowMapper.selectByExample(example);
        if (boyingShows == null || boyingShows.size() == 0) {
            Asserts.fail("查询不到演出信息!");
        }

        List<BoyingShowReturn> list = new LinkedList<>();

        for (BoyingShow boyingShow : boyingShows) {
            BoyingShowReturn boyingShowReturn = new BoyingShowReturn();
            BeanUtils.copyProperties(boyingShow, boyingShowReturn);
            BoyingCategory boyingCategory = boyingCategoryMapper.selectByPrimaryKey(boyingShow.getCategoryId());
            if(boyingCategory==null) Asserts.fail("该演出不属于任何一个目录了！");
            boyingShowReturn.setCategory(boyingCategory.getName());
            list.add(boyingShowReturn);
        }
        return list;
    }

    @Override
    public void create(SmsShowParam param) {
        //演出名称不能重复
        checkBoyingShowParam(param);
        BoyingShow show = new BoyingShow();
        BeanUtils.copyProperties(param, show);
        int count = boyingShowMapper.insertSelective(show);
        if(count==0) Asserts.fail("创建演出成功！");
    }

    @Override
    public void update(Integer id, SmsShowParam param) {
        //演出名称不能重复
        BoyingShowExample boyingShowExample = new BoyingShowExample();
        BoyingShowExample.Criteria criteria = boyingShowExample.createCriteria();
        criteria.andNameEqualTo(param.getName());
        List<BoyingShow> shows = boyingShowMapper.selectByExample(boyingShowExample);

        /*if (CollUtil.isNotEmpty(shows)) {
            //说明还不是修改本身
            if (!shows.get(0).getId().equals(id)) {
                Asserts.fail("演出名称不能重复!");
            }
        }*/

        BoyingShow show = new BoyingShow();

        BeanUtils.copyProperties(param, show);
        show.setId(id);
        int count = boyingShowMapper.updateByPrimaryKeySelective(show);
        if(count==0) Asserts.fail("更新演出成功！");
    }

    @Override
    public BoyingShow detail(Integer id) {
        BoyingShow boyingShow = boyingShowMapper.selectByPrimaryKey(id);
        if (boyingShow == null) Asserts.fail("演出信息不存在！");
        return boyingShow;
    }


    private void checkBoyingShowParam(SmsShowParam param) {
        BoyingShowExample boyingShowExample = new BoyingShowExample();
        BoyingShowExample.Criteria criteria = boyingShowExample.createCriteria();
        criteria.andNameEqualTo(param.getName());
        List<BoyingShow> shows = boyingShowMapper.selectByExample(boyingShowExample);
        if (CollUtil.isNotEmpty(shows)) {
            Asserts.fail("演出名称不能重复!");
        }
    }
}
