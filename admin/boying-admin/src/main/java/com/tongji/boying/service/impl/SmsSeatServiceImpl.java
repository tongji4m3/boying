package com.tongji.boying.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.SmsSeatParam;
import com.tongji.boying.mapper.BoyingSeatMapper;
import com.tongji.boying.mapper.BoyingShowMapper;
import com.tongji.boying.mapper.BoyingStockMapper;
import com.tongji.boying.model.*;
import com.tongji.boying.service.SmsSeatService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmsSeatServiceImpl implements SmsSeatService {
    @Autowired
    private BoyingSeatMapper boyingSeatMapper;

    @Autowired
    private BoyingShowMapper boyingShowMapper;

    @Autowired
    private BoyingStockMapper boyingStockMapper;
    @Override
    public int create(SmsSeatParam param)
    {
//        int insert = orderMapper.insertSelective(order);
//        //生成票
//        for (Integer showSeatId : showSeatIds) {
//            System.out.println(order.getId() + " " + showSeatId);
//            userTicketService.add(order.getId(), showSeatId);
//        }

        checkBoyingSeatParam(param, -1);
        BoyingSeat seat = new BoyingSeat();
        BeanUtils.copyProperties(param, seat);
        System.out.println("helloworld");
        System.out.println(seat);
        int insert=boyingSeatMapper.insertSelective(seat);
        BoyingStock stock=new BoyingStock();
        stock.setId(seat.getId());
        stock.setStock(seat.getCapacity());
        boyingStockMapper.insert(stock);
        return insert;
    }

    @Override
    public int update(Integer id, SmsSeatParam param) {
        checkBoyingSeatParam(param, id);
        BoyingSeat seat = new BoyingSeat();
        BeanUtils.copyProperties(param, seat);
        seat.setId(id);
        return boyingSeatMapper.updateByPrimaryKeySelective(seat);
    }

    @Override
    public int delete(Integer id) {
        return boyingSeatMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<BoyingSeat> list() {
        return boyingSeatMapper.selectByExample(new BoyingSeatExample());
    }

    @Override
    public List<BoyingSeat> getShowSeat(Integer id)
    {
        BoyingSeatExample example = new BoyingSeatExample();
        example.createCriteria().andShowIdEqualTo(id);
        return boyingSeatMapper.selectByExample(example);
    }

    private void checkBoyingSeatParam(SmsSeatParam param, Integer id)
    {
        BoyingShowExample boyingShowExample = new BoyingShowExample();
        BoyingShowExample.Criteria criteria = boyingShowExample.createCriteria();
        BoyingSeatExample boyingSeatExample=new BoyingSeatExample();
        BoyingSeatExample.Criteria criteria1=boyingSeatExample.createCriteria();
        criteria.andIdEqualTo(param.getShowId());
        if (id != -1)
        {
            criteria.andIdNotEqualTo(id);
        }
        else{
            criteria1.andShowIdEqualTo(param.getShowId());
            criteria1.andNameEqualTo(param.getName());
            List<BoyingSeat> seats=boyingSeatMapper.selectByExample(boyingSeatExample);
            if(CollUtil.isNotEmpty(seats))
            {
                Asserts.fail("不能重复添加座次");
            }
        }
        List<BoyingShow> shows = boyingShowMapper.selectByExample(boyingShowExample);
        if (CollUtil.isEmpty(shows))
        {
            Asserts.fail("没有此演出，无法为其添加座次!");
        }
    }
}
