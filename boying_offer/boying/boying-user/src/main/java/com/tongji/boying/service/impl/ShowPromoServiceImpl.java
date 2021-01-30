package com.tongji.boying.service.impl;

import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.mapper.BoyingPromoMapper;
import com.tongji.boying.model.BoyingPromo;
import com.tongji.boying.service.ShowPromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowPromoServiceImpl implements ShowPromoService {
    @Autowired
    private BoyingPromoMapper promoMapper;

    @Override
    public BoyingPromo getPromo(Integer seatId) {
        BoyingPromo boyingPromo = promoMapper.selectBySeatId(seatId);
        if (boyingPromo == null) {
            Asserts.fail("对应的活动不存在！");
        }
        return boyingPromo;
    }
}
