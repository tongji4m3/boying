package com.tongji.boying.service;

import com.tongji.boying.model.ShowClass;

import java.util.List;

public interface ShowClassService
{
    ShowClass detail(Integer classId);

    List<ShowClass> getShowClassList(int sessionId, Integer pageNum, Integer pageSize);
}
