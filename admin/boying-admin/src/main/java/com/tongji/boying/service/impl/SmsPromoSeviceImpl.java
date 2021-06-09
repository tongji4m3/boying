package com.tongji.boying.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.UmsPromoParam;
import com.tongji.boying.mapper.BoyingPromoMapper;
import com.tongji.boying.mapper.BoyingSeatMapper;
import com.tongji.boying.mapper.BoyingShowMapper;
import com.tongji.boying.model.BoyingPromo;
import com.tongji.boying.model.BoyingPromoExample;
import com.tongji.boying.model.BoyingSeat;
import com.tongji.boying.model.BoyingShow;
import com.tongji.boying.service.SmsPromoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 后台活动管理Service实现类
 */
@Service
public class SmsPromoSeviceImpl implements SmsPromoService
{
    @Autowired
    private BoyingPromoMapper boyingPromoMapper;
    @Autowired
    private BoyingSeatMapper boyingSeatMapper;
    @Autowired
    private BoyingShowMapper boyingShowMapper;

    @Override
    public List<BoyingPromo> list()
    {
        return boyingPromoMapper.selectByExample(new BoyingPromoExample());
    }

    @Override
    public BoyingPromo getPromo(Integer id)
    {
        return boyingPromoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int create(UmsPromoParam param)
    {
        BoyingPromoExample boyingPromoExample = new BoyingPromoExample();
        boyingPromoExample.createCriteria().andSeatIdEqualTo(param.getSeatId());
        List<BoyingPromo> boyingPromos = boyingPromoMapper.selectByExample(boyingPromoExample);
        BoyingSeat seat = boyingSeatMapper.selectByPrimaryKey(param.getSeatId());
        BoyingShow show = boyingShowMapper.selectByPrimaryKey(seat.getShowId());
        if (!(param.getEndTime().before(show.getEndTime()) && param.getStartTime().after(show.getStartTime())))
        {
            Asserts.fail("该活动时间未位于演出时间范围内!");
        }
        for (BoyingPromo boyingPromo : boyingPromos)
        {
            if (boyingPromo.getEndTime().after(new Date()))
            {
                Asserts.fail("该座次有未结束活动!");
            }
        }

        BoyingPromo promo = new BoyingPromo();
        BeanUtils.copyProperties(param, promo);
        return boyingPromoMapper.insertSelective(promo);
    }

    public int update(Integer id, UmsPromoParam param)
    {
        BoyingPromo promo = new BoyingPromo();

        BeanUtils.copyProperties(param, promo);
        promo.setId(id);
        return boyingPromoMapper.updateByPrimaryKeySelective(promo);
    }

    @Override
    public int delete(Integer id)
    {
        return boyingPromoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delete(List<Integer> ids)
    {
        BoyingPromoExample example = new BoyingPromoExample();
        example.createCriteria().andIdIn(ids);
        if (boyingPromoMapper.selectByExample(example).size() != ids.size())
        {
            Asserts.fail("某些演出Id不存在！");
        }
        return boyingPromoMapper.deleteByExample(example);
    }

    private void checkBoyingPromoParam(UmsPromoParam param, Integer id)
    {
        BoyingPromoExample boyingPromoExample = new BoyingPromoExample();
        BoyingPromoExample.Criteria criteria = boyingPromoExample.createCriteria();
        criteria.andNameEqualTo(param.getName());
        if (id != -1)
        {
            criteria.andIdNotEqualTo(id);
        }
        List<BoyingPromo> promos = boyingPromoMapper.selectByExample(boyingPromoExample);
        if (CollUtil.isNotEmpty(promos))
        {
            Asserts.fail("活动名称不能重复");
        }
    }
}
