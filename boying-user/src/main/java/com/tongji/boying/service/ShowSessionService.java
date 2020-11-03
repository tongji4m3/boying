package com.tongji.boying.service;

import com.tongji.boying.model.ShowSession;

import java.util.List;

public interface ShowSessionService
{
    ShowSession detail(Integer sessionId);

    List<ShowSession> getShowSessionList(int id, Integer pageNum, Integer pageSize);
}
