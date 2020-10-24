package com.tongji.boying.service;


import com.tongji.boying.model.Show;

import java.util.Date;
import java.util.List;

/**
 * 前台演出管理Service
 */
public interface ShowService
{
    /**
     * 综合搜索演出
     * 关键词,城市,目录,时间
     * 每页条数,分页数
     * 搜索方式(相关度搜索,推荐搜索,时间搜索)
     */
    List<Show> search(String keyword, String city, Integer categoryId, Date date, Integer pageNum, Integer pageSize, Integer sort);


    /**
     * 获取前台演出详情
     */
    Show detail(int id);
}
