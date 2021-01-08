package com.tongji.boying.service.impl;

import com.tongji.boying.dto.SmsSessionParam;
import com.tongji.boying.mapper.ShowSessionMapper;
import com.tongji.boying.model.Role;
import com.tongji.boying.model.ShowSession;
import com.tongji.boying.model.ShowSessionExample;
import com.tongji.boying.service.SmsSessionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SmsSessionService实现类
 */
@Service
public class SmsSessionServiceImpl implements SmsSessionService
{
    @Autowired
    private ShowSessionMapper showSessionMapper;

    @Override
    public List<ShowSession> list(Integer showId)
    {
        ShowSessionExample example = new ShowSessionExample();
        example.createCriteria().andShowIdEqualTo(showId);
        return showSessionMapper.selectByExample(example);
    }

    @Override
    public int create(SmsSessionParam param)
    {
        ShowSession showSession = new ShowSession();
        BeanUtils.copyProperties(param, showSession);
        return showSessionMapper.insertSelective(showSession);
    }

    @Override
    public int update(Integer id, SmsSessionParam param)
    {
        ShowSession showSession = new ShowSession();
        BeanUtils.copyProperties(param, showSession);
        showSession.setShowSessionId(id);
        return showSessionMapper.updateByPrimaryKeySelective(showSession);
    }

    @Override
    public int delete(Integer id)
    {
        return showSessionMapper.deleteByPrimaryKey(id);
    }
}
