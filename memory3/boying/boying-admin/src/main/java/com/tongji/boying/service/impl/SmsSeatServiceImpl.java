package com.tongji.boying.service.impl;


import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.PageHelper;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.showParam.ShowSeatAddParam;
import com.tongji.boying.dto.showParam.ShowSeatListParam;
import com.tongji.boying.mapper.BoyingSeatMapper;
import com.tongji.boying.mapper.BoyingShowMapper;
import com.tongji.boying.model.BoyingSeat;
import com.tongji.boying.model.BoyingSeatExample;
import com.tongji.boying.model.BoyingShow;
import com.tongji.boying.model.BoyingShowExample;
import com.tongji.boying.service.SmsSeatService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmsSeatServiceImpl implements SmsSeatService {
    @Autowired
    BoyingSeatMapper boyingSeatMapper;
    @Autowired
    BoyingShowMapper boyingShowMapper;

    @Override
    public void create(ShowSeatAddParam param) {
        //座次名称不能重复
        //stock不能超出容量，也不能小于等于0;默认等于容量
        Integer stock = param.getStock();
        if (stock == null) {
            stock = param.getCapacity();
        }
        else if (stock <= 0 || stock > param.getCapacity()) {
            Asserts.fail("库存设置错误！");
        }

        //查看座次名称是否重复 只需要该演出的座次不重复即可
        BoyingSeatExample boyingSeatExample = new BoyingSeatExample();
        boyingSeatExample.createCriteria().andNameEqualTo(param.getName()).andShowIdEqualTo(param.getShowId());
        List<BoyingSeat> boyingSeats = boyingSeatMapper.selectByExample(boyingSeatExample);
        if (CollUtil.isNotEmpty(boyingSeats)) {
            Asserts.fail("演出座次名称不能重复!");
        }

        BoyingSeat boyingSeat = new BoyingSeat();
        BeanUtils.copyProperties(param, boyingSeat);
        boyingSeat.setStock(stock);

        int count = boyingSeatMapper.insertSelective(boyingSeat);
        if (count == 0) Asserts.fail("创建演出座次失败！");

        //修改演出的最低价，最高价
        double price = param.getPrice();
        double minPrice = param.getPrice();
        double maxPrice = param.getPrice();
        for (BoyingSeat seat : boyingSeats) {
            minPrice = Math.min(minPrice, seat.getPrice());
            maxPrice = Math.max(maxPrice, seat.getPrice());
        }

        //说明没有触及最低价，最高价
        if (minPrice != price && maxPrice != price) {
            return;
        }
        BoyingShow boyingShow = new BoyingShow();
        boyingShow.setId(param.getShowId());
        boyingShow.setMinPrice(minPrice);
        boyingShow.setMaxPrice(maxPrice);
        boyingShowMapper.updateByPrimaryKeySelective(boyingShow);
    }

    @Override
    public List<BoyingSeat> getShowSeatList(ShowSeatListParam param) {
        BoyingSeatExample example = new BoyingSeatExample();
        BoyingSeatExample.Criteria criteria = example.createCriteria();

        //根据演出Id搜索
        if (param.getShowId() != null && param.getShowId() != 0) {
            criteria.andShowIdEqualTo(param.getShowId());
        }

        example.setOrderByClause("name");

        Integer pageNum = param.getPageNum();
        if (pageNum == null || pageNum == 0) pageNum = 1;
        Integer pageSize = param.getPageSize();
        if (pageSize == null || pageSize == 0) pageSize = 5;

        PageHelper.startPage(pageNum, pageSize);
        List<BoyingSeat> boyingSeats = boyingSeatMapper.selectByExample(example);
        if (boyingSeats == null || boyingSeats.size() == 0) {
            Asserts.fail("演出座次不存在！");
        }
        return boyingSeats;
    }
}
