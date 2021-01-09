package com.tongji.boying.service.impl;


import com.tongji.boying.dto.SmsSessionParam;
import com.tongji.boying.mapper.BoyingSeatMapper;
import com.tongji.boying.model.BoyingSeat;
import com.tongji.boying.model.BoyingSeatExample;
import com.tongji.boying.service.SmsSeatService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmsSeatServiceImpl implements SmsSeatService
{
    @Autowired
    BoyingSeatMapper boyingSeatMapper;

    @Override
    public List<BoyingSeat> list(Integer showId)
    {
        BoyingSeatExample example = new BoyingSeatExample();
        example.createCriteria().andShowIdEqualTo(showId);
        return boyingSeatMapper.selectByExample(example);
    }

    @Override
    public int create(SmsSessionParam param)
    {
        BoyingSeat boyingSeat = new BoyingSeat();
        BeanUtils.copyProperties(param, boyingSeat);
        return boyingSeatMapper.insertSelective(boyingSeat);
    }

    @Override
    public int update(Integer id, SmsSessionParam param)
    {
        BoyingSeat boyingSeat = new BoyingSeat();
        BeanUtils.copyProperties(param, boyingSeat);
        boyingSeat.setId(id);
        return boyingSeatMapper.updateByPrimaryKeySelective(boyingSeat);
    }

    @Override
    public int delete(Integer id)
    {
        return boyingSeatMapper.deleteByPrimaryKey(id);
    }
}
