package com.tongji.boying.service.impl;

import com.tongji.boying.common.common.PromoEnum;
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

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoyingSeatServiceImpl implements BoyingSeatService {
    @Resource
    private BoyingSeatMapper boyingSeatMapper;

    @Autowired
    private BoyingStockService boyingStockService;

    @Autowired
    private BoyingPromoService boyingPromoService;

    @Override
    public List<BoyingSeatModel> getShowSeatList(Integer showId) {
        List<BoyingSeat> boyingSeats = boyingSeatMapper.selectList(showId);
        return boyingSeats.stream().map(this::convertModelFromDataObject).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Integer decreaseStock(Integer seatId, Integer ticketCount) {
        if (!(seatId >= 1 && seatId <= 280) || !(ticketCount >= 1 && ticketCount <= 3)) {
            return 0;
        }
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
        if (seatDO == null) return null;
        return convertModelFromDataObject(seatDO);
    }

    private BoyingSeatModel convertModelFromDataObject(BoyingSeat seatDO) {
        BoyingStock stockDO = boyingStockService.selectByPrimaryKey(seatDO.getId());

        BoyingSeatModel boyingSeatModel = new BoyingSeatModel();
        BeanUtils.copyProperties(seatDO, boyingSeatModel);
        boyingSeatModel.setStock(stockDO.getStock());

        return boyingSeatModel;
    }
}
