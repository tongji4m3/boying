package com.tongji.boying.service;


import com.tongji.boying.model.AdminCategory;
import com.tongji.boying.model.BoyingHistory;

import java.util.List;

/**
 * 后台订单历史Service
 */
public interface UmsHistoryService
{
    /**
     * 获取所有订单历史
     */
    List<BoyingHistory> listAll();
}
