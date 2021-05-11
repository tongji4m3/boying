package com.tongji.boying.service;


import com.tongji.boying.dto.SmsSeatParam;
import com.tongji.boying.model.BoyingSeat;
import com.tongji.boying.model.BoyingShow;

import java.util.List;

public interface SmsSeatService {
    /**
     * 新建座次
     */
    int create(SmsSeatParam param);

    /**
     * 修改座次
     */
    int update(Integer id, SmsSeatParam param);

    /**
     * 删除座次
     */
    int delete(Integer id);

    /**
     * 查看所有座次
     *
     */
    List<BoyingSeat> list();



    /**
     * 查看某个show的所有座次
     *
     */
    List<BoyingSeat> getShowSeat(Integer id);
}
