package com.tongji.boying.service;


import com.tongji.boying.dto.SmsSeatParam;
import com.tongji.boying.model.BoyingSeat;

public interface SmsSeatService {
    /**
     * 新建座次
     */
    int create(SmsSeatParam param);
}
