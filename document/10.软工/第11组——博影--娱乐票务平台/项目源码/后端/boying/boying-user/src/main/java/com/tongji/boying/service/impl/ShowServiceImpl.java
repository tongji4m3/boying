package com.tongji.boying.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.showParam.BoyingShowReturn;
import com.tongji.boying.dto.showParam.ShowParam;
import com.tongji.boying.mapper.BoyingSeatMapper;
import com.tongji.boying.mapper.BoyingShowMapper;
import com.tongji.boying.model.BoyingSeat;
import com.tongji.boying.model.BoyingSeatExample;
import com.tongji.boying.model.BoyingShow;
import com.tongji.boying.model.BoyingShowExample;
import com.tongji.boying.service.ShowService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ShowServiceImpl implements ShowService {
    @Autowired
    private BoyingShowMapper showMapper;
    @Autowired
    private BoyingSeatMapper seatMapper;

    @Override
    public List<BoyingShowReturn> search(ShowParam param) {
        BoyingShowExample example = new BoyingShowExample();
        BoyingShowExample.Criteria criteria = example.createCriteria();

        if (StrUtil.isNotEmpty(param.getKeyword())) {
            criteria.andNameLike("%" + param.getKeyword() + "%");
        }

        if (StrUtil.isNotEmpty(param.getCity()) && !param.getCity().equals("全国")) {
            criteria.andCityEqualTo(param.getCity());
        }

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
        List<BoyingShowReturn> boyingShowReturns = new LinkedList<>();
        for (BoyingShow boyingShow : boyingShows) {

            BoyingShowReturn boyingShowReturn = new BoyingShowReturn();
            BeanUtils.copyProperties(boyingShow, boyingShowReturn);
            BoyingSeatExample boyingSeatExample = new BoyingSeatExample();
            boyingSeatExample.createCriteria().andShowIdEqualTo(boyingShow.getId());
            List<BoyingSeat> boyingSeats = seatMapper.selectByExample(boyingSeatExample);
            if(boyingSeats==null || boyingSeats.size()==0) Asserts.fail("演出座次不存在！");
            Double minPrice = Double.MAX_VALUE;
            Double maxPrice = 0d;
            for (BoyingSeat boyingSeat : boyingSeats) {
                minPrice = Math.min(boyingSeat.getPrice(), minPrice);
                maxPrice = Math.max(boyingSeat.getPrice(), maxPrice);
            }
            boyingShowReturn.setMinPrice(minPrice);
            boyingShowReturn.setMaxPrice(maxPrice);
            boyingShowReturns.add(boyingShowReturn);
        }
        return boyingShowReturns;
    }

    @Override
    public BoyingShowReturn detail(int id) {
        BoyingShow boyingShow = showMapper.selectByPrimaryKey(id);
        if (boyingShow == null) Asserts.fail("演出信息不存在！");
        BoyingShowReturn boyingShowReturn = new BoyingShowReturn();
        BeanUtils.copyProperties(boyingShow, boyingShowReturn);
        BoyingSeatExample boyingSeatExample = new BoyingSeatExample();
        boyingSeatExample.createCriteria().andShowIdEqualTo(id);
        List<BoyingSeat> boyingSeats = seatMapper.selectByExample(boyingSeatExample);
        if(boyingSeats==null || boyingSeats.size()==0) Asserts.fail("演出座次不存在！");
        Double minPrice = Double.MAX_VALUE;
        Double maxPrice = 0d;
        for (BoyingSeat boyingSeat : boyingSeats) {
            minPrice = Math.min(boyingSeat.getPrice(), minPrice);
            maxPrice = Math.max(boyingSeat.getPrice(), maxPrice);
        }
        boyingShowReturn.setMinPrice(minPrice);
        boyingShowReturn.setMaxPrice(maxPrice);
        return boyingShowReturn;
    }
}
