package com.tongji.boying.service.impl;

import com.github.pagehelper.PageHelper;
import com.tongji.boying.mapper.ShowSessionMapper;
import com.tongji.boying.model.ShowSession;
import com.tongji.boying.model.ShowSessionExample;
import com.tongji.boying.service.ShowSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowSessionServiceImpl implements ShowSessionService {
    @Autowired
    private ShowSessionMapper showSessionMapper;

    @Override
    public ShowSession detail(Integer sessionId) {
        return showSessionMapper.selectByPrimaryKey(sessionId);
    }

    @Override
    public List<ShowSession> getShowSessionList(int id, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);//分页相关
        ShowSessionExample showSessionExample = new ShowSessionExample();
        showSessionExample.createCriteria().andShowIdEqualTo(id);
        return showSessionMapper.selectByExample(showSessionExample);
    }
}
