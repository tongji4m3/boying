package com.tongji.boying.service.impl;

import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.common.service.RedisService;
import com.tongji.boying.mapper.BoyingSeatMapper;
import com.tongji.boying.model.*;
import com.tongji.boying.service.BoyingPromoService;
import com.tongji.boying.service.BoyingSeatService;
import com.tongji.boying.service.BoyingStockService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoyingSeatServiceImpl implements BoyingSeatService {
    @Autowired
    private BoyingSeatMapper boyingSeatMapper;
    @Autowired
    private BoyingStockService boyingStockService;

    @Autowired
    private BoyingPromoService boyingPromoService;

    @Autowired
    private RedisService redisService;

    @Override
    public List<BoyingSeatModel> getShowSeatList(Integer showId) {
        List<BoyingSeat> boyingSeats = (List<BoyingSeat>) redisService.get("boying_show_seats:" + showId);

        if (boyingSeats == null) {
            boyingSeats = boyingSeatMapper.selectList(showId);
            if (boyingSeats == null || boyingSeats.size() == 0) {
                Asserts.fail("演出座次不存在！");
            }
            redisService.set("boying_show_seats:" + showId, boyingSeats);
        }

        return boyingSeats.stream().map(this::convertModelFromDataObject).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Integer decreaseStock(Integer seatId, Integer ticketCount) {
        return boyingSeatMapper.decreaseStock(seatId, ticketCount);
    }

    @Override
    @Transactional
    public void increaseStock(Integer seatId, Integer ticketCount) {
        boyingSeatMapper.increaseStock(seatId, ticketCount);
    }

    @Override
    public BoyingSeatModel getShowSeat(Integer seatId) {
        BoyingSeat seatDO = boyingSeatMapper.selectByPrimaryKey(seatId);
        return convertModelFromDataObject(seatDO);
    }

    private BoyingSeatModel convertModelFromDataObject(BoyingSeat seatDO) {
        BoyingStock stockDO = boyingStockService.selectByPrimaryKey(seatDO.getId());


        BoyingSeatModel boyingSeatModel = new BoyingSeatModel();
        BeanUtils.copyProperties(seatDO, boyingSeatModel);
        boyingSeatModel.setStock(stockDO.getStock());

        BoyingPromoModel boyingPromoModel = boyingPromoService.getPromo(seatDO.getId());
        //存在秒杀活动，而且是未开始或者是正在进行中的
        if (boyingPromoModel != null && boyingPromoModel.getStatus() != 3) {
            boyingSeatModel.setBoyingPromoModel(boyingPromoModel);
        }
        return boyingSeatModel;
    }
}
