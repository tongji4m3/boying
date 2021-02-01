package com.tongji.boying.service.impl;

import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.mapper.BoyingPromoMapper;
import com.tongji.boying.model.BoyingPromo;
import com.tongji.boying.model.BoyingPromoModel;
import com.tongji.boying.service.BoyingPromoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BoyingPromoServiceImpl implements BoyingPromoService {
    @Autowired
    private BoyingPromoMapper boyingPromoMapper;

    @Override
    public BoyingPromoModel getPromo(Integer seatId) {
        //获取对应演出的秒杀活动信息
        BoyingPromo promoDO = boyingPromoMapper.selectBySeatId(seatId);
        if (promoDO == null) {
            Asserts.fail("对应的活动不存在！");
        }
        //dataObject->model
        BoyingPromoModel boyingPromoModel = new BoyingPromoModel();
        BeanUtils.copyProperties(promoDO, boyingPromoModel);

        //判断当前时间是否秒杀活动即将开始或正在进行
        if (boyingPromoModel.getStartTime().before(new Date())) {
            boyingPromoModel.setStatus(1);
        } else if (boyingPromoModel.getEndTime().before(new Date())) {
            boyingPromoModel.setStatus(3);
        } else {
            boyingPromoModel.setStatus(2);
        }
        return boyingPromoModel;
    }
}
