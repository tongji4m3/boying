package com.tongji.boying.service;


import com.tongji.boying.dto.showParam.ShowParam;
import com.tongji.boying.model.BoyingShow;

import java.util.Date;
import java.util.List;

/**
 * 前台演出管理Service
 */
public interface BoyingShowService {
    /**
     * 综合搜索演出
     * 关键词,城市,目录,时间
     * 每页条数,分页数
     * 搜索方式(推荐搜索,时间搜索,价格搜索)
     */
    List<BoyingShow> search(ShowParam param);

    BoyingShow detail(int id);
}
