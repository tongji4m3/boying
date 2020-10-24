package com.tongji.boying.service.impl;

import com.tongji.boying.mapper.ShowClassMapper;
import com.tongji.boying.model.ShowClass;
import com.tongji.boying.model.ShowClassExample;
import com.tongji.boying.service.ShowClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowClassServiceImpl implements ShowClassService
{
    @Autowired
    private ShowClassMapper showClassMapper;

    @Override
    public ShowClass detail(Integer classId)
    {
        return showClassMapper.selectByPrimaryKey(classId);
    }

    @Override
    public List<ShowClass> getShowClassList(int sessionId)
    {
        ShowClassExample showClassExample = new ShowClassExample();
        showClassExample.createCriteria().andShowSessionIdEqualTo(sessionId);
        return showClassMapper.selectByExample(showClassExample);
    }
}
