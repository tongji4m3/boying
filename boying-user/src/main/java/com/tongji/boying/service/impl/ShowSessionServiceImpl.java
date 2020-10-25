package com.tongji.boying.service.impl;

import com.tongji.boying.mapper.ShowSessionMapper;
import com.tongji.boying.model.ShowSession;
import com.tongji.boying.model.ShowSessionExample;
import com.tongji.boying.service.ShowSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowSessionServiceImpl implements ShowSessionService
{
    @Autowired
    private ShowSessionMapper showSessionMapper;

    @Override
    public ShowSession detail(Integer sessionId)
    {
        return showSessionMapper.selectByPrimaryKey(sessionId);
    }

    @Override
    public List<ShowSession> getShowSessionList(int showId)
    {
        ShowSessionExample showSessionExample = new ShowSessionExample();
        showSessionExample.createCriteria().andShowIdEqualTo(showId);
        return showSessionMapper.selectByExample(showSessionExample);
    }
}
