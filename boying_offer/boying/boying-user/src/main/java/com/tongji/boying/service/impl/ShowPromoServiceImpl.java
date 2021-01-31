package com.tongji.boying.service.impl;

import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.mapper.BoyingPromoMapper;
import com.tongji.boying.model.BoyingPromo;
import com.tongji.boying.model.PromoModel;
import com.tongji.boying.service.ShowPromoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class ShowPromoServiceImpl implements ShowPromoService {
    @Autowired
    private BoyingPromoMapper promoMapper;

    @Override
    public PromoModel getPromo(Integer seatId) {
        //获取对应演出的秒杀活动信息
        BoyingPromo promoDO = promoMapper.selectBySeatId(seatId);
        if (promoDO == null) {
            Asserts.fail("对应的活动不存在！");
        }
        //dataObject->model
        PromoModel promoModel = convertFromDataObject(promoDO);

        //判断当前时间是否秒杀活动即将开始或正在进行
        if (promoModel.getStartTime().before(new Date())) {
            promoModel.setStatus(1);
        } else if (promoModel.getEndTime().before(new Date())) {
            promoModel.setStatus(3);
        } else {
            promoModel.setStatus(2);
        }
        return promoModel;
    }

    private PromoModel convertFromDataObject(BoyingPromo promoDO) {
        if (promoDO == null) {
            return null;
        }
        PromoModel promoModel = new PromoModel();
        BeanUtils.copyProperties(promoDO, promoModel);
        return promoModel;
    }
}
