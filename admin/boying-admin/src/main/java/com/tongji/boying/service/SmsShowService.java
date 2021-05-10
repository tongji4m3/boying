package com.tongji.boying.service;

/**
 * @author cheng fu
 * @Description TODO
 * @date 2020/11/3-23:27
 */


import com.tongji.boying.dto.SmsShowParam;
import com.tongji.boying.model.BoyingShow;

import java.util.List;

/**
 * 后台演出管理Service
 */
public interface SmsShowService
{
    /**
     * 查看演出
     *
     */
    List<BoyingShow> list();
    /**
     * 添加演出
     *
     * @param param
     */
    int create(SmsShowParam param);

    /**
     * 修改演出
     */
    int update(Integer id, SmsShowParam param);

    /**
     * 批量删除演出
     */
    int delete(List<Integer> ids);

    /**
     * 删除演出
     */
    int delete(Integer id);


}

