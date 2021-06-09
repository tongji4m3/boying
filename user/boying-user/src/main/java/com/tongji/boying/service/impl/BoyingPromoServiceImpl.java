package com.tongji.boying.service.impl;

import com.tongji.boying.common.common.PromoEnum;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.common.service.RedisService;
import com.tongji.boying.mapper.BoyingPromoMapper;
import com.tongji.boying.model.BoyingPromo;
import com.tongji.boying.model.BoyingPromoModel;
import com.tongji.boying.service.BoyingPromoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class BoyingPromoServiceImpl implements BoyingPromoService {
    @Resource
    private BoyingPromoMapper boyingPromoMapper;

    // todo 之后加上缓存，并且要防止缓存穿透
    @Override
    public BoyingPromoModel getPromo(Integer seatId) {
        // todo 活动有多个的问题
        BoyingPromo promoDO = boyingPromoMapper.selectBySeatId(seatId);

        BoyingPromoModel boyingPromoModel = new BoyingPromoModel();
        BeanUtils.copyProperties(promoDO, boyingPromoModel);

        // 判断当前时间是否秒杀活动即将开始或正在进行
        // 秒杀活动状态 1表示还未开始，2表示进行中，3表示已结束
        if (boyingPromoModel.getStartTime().after(new Date())) {
            boyingPromoModel.setStatus(PromoEnum.NOT_START.getValue());
        } else if (boyingPromoModel.getEndTime().before(new Date())) {
            boyingPromoModel.setStatus(PromoEnum.FINISH.getValue());
        } else {
            boyingPromoModel.setStatus(PromoEnum.DOING_PROMO.getValue());
        }
        return boyingPromoModel;
    }
}
