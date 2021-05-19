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

    @Autowired
    private RedisService redisService;

    @Override
    public BoyingPromoModel getPromo(Integer seatId) {
        BoyingPromo promoDO = (BoyingPromo) redisService.get("boying_promo:" + seatId);

        //说明数据库中不存在数据，避免缓存穿透
        if (promoDO != null && promoDO.getId() == -1) {
            return null;
        }
        if (promoDO == null) {
            //获取对应演出座次的秒杀活动信息
            promoDO = boyingPromoMapper.selectBySeatId(seatId);
            if (promoDO == null) {
                redisService.set("boying_promo:" + seatId, new BoyingPromo(-1), 5);
                return null;
            }
            redisService.set("boying_promo:" + seatId, promoDO);
        }


        //dataObject->model
        BoyingPromoModel boyingPromoModel = new BoyingPromoModel();
        BeanUtils.copyProperties(promoDO, boyingPromoModel);

        //判断当前时间是否秒杀活动即将开始或正在进行
        //秒杀活动状态 1表示还未开始，2表示进行中，3表示已结束
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
