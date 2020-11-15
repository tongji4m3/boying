package com.tongji.boying.service;

import com.tongji.boying.dto.SmsSessionParam;
import com.tongji.boying.model.ShowSession;

import java.util.List;

/**
 * 后台演出场次Service
 */
public interface SmsSessionService
{
    /**
     * 查看某个演出的所有场次
     */
    List<ShowSession> list(Integer showId);

    /**
     * 为某个演出的添加场次
     */
    int create(SmsSessionParam param);

    /**
     * 修改某个演出的场次
     */
    int update(Integer id, SmsSessionParam param);


    /**
     * 删除某个演出的场次
     */
    int delete(Integer id);
}
