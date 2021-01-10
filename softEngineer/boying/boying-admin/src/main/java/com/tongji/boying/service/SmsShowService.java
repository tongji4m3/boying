package com.tongji.boying.service;

import com.tongji.boying.dto.showParam.SmsShowListParam;
import com.tongji.boying.dto.showParam.SmsShowParam;
import com.tongji.boying.model.BoyingShow;

import java.util.List;

/**
 * 后台演出管理Service
 */
public interface SmsShowService {
    /**
     * 查看演出
     * @param param
     */
    List<BoyingShow> list(SmsShowListParam param);

    /**
     * 添加演出
     *
     * @param param
     */
    void create(SmsShowParam param);

    /**
     * 修改演出
     */
    void update(Integer id, SmsShowParam param);

    BoyingShow detail(Integer id);
}

