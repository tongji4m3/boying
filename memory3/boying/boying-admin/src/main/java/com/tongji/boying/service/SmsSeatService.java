package com.tongji.boying.service;


import com.tongji.boying.dto.showParam.ShowSeatAddParam;
import com.tongji.boying.dto.showParam.ShowSeatListParam;
import com.tongji.boying.model.BoyingSeat;

import java.util.List;

public interface SmsSeatService
{
    /**
     * 为某个演出的添加场次
     */
    void create(ShowSeatAddParam param);


    List<BoyingSeat> getShowSeatList(ShowSeatListParam param);
}
