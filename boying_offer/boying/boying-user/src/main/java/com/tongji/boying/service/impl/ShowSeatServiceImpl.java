package com.tongji.boying.service.impl;

import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.mapper.BoyingPromoMapper;
import com.tongji.boying.mapper.BoyingSeatMapper;
import com.tongji.boying.mapper.BoyingStockMapper;
import com.tongji.boying.model.*;
import com.tongji.boying.service.ShowPromoService;
import com.tongji.boying.service.ShowSeatService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowSeatServiceImpl implements ShowSeatService {
    @Autowired
    private BoyingSeatMapper showSeatMapper;
    @Autowired
    private BoyingStockMapper boyingStockMapper;

    @Autowired
    private ShowPromoService showPromoService;

    @Override
    public List<ItemModel> getShowSeatList(Integer showId) {
        List<BoyingSeat> boyingSeats = showSeatMapper.selectList(showId);
        if (boyingSeats == null || boyingSeats.size() == 0) {
            Asserts.fail("演出座次不存在！");
        }
        return boyingSeats.stream().map(seatDO -> {
            BoyingStock stockDO = boyingStockMapper.selectByPrimaryKey(seatDO.getId());

            PromoModel promoModel = showPromoService.getPromo(seatDO.getId());

            ItemModel itemModel = this.convertModelFromDataObject(seatDO, stockDO);

            //存在秒杀活动，而且是未开始或者是正在进行中的
            if (promoModel != null && promoModel.getStatus() != 3) {
                itemModel.setPromoModel(promoModel);
            }
            return itemModel;
        }).collect(Collectors.toList());
    }

    private ItemModel convertModelFromDataObject(BoyingSeat seatDO, BoyingStock stockDO) {
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(seatDO, itemModel);
        itemModel.setStock(stockDO.getStock());
        return itemModel;
    }

    @Override
    public Integer getSeatStock(Integer seatId) {
        return showSeatMapper.getSeatStock(seatId);
    }

    @Override
    public BoyingSeat selectByPrimaryKey(Integer seatId) {
        return showSeatMapper.selectByPrimaryKey(seatId);
    }

    @Override
    @Transactional
    public Integer decreaseStock(Integer seatId, Integer ticketCount) {
        return showSeatMapper.decreaseStock(seatId, ticketCount);
    }

    @Override
    @Transactional
    public void increaseStock(Integer seatId, Integer ticketCount) {
        showSeatMapper.increaseStock(seatId, ticketCount);
    }
}
