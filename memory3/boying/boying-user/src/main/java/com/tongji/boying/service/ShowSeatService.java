package com.tongji.boying.service;

import com.tongji.boying.model.BoyingSeat;

import java.util.List;

public interface ShowSeatService {
    List<BoyingSeat> getShowSeatList(Integer showId);
}
