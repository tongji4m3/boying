package com.tongji.boying.service;

import com.tongji.boying.dto.UmsRoleParam;
import com.tongji.boying.dto.UmsShowParam;

/**
 * 后台演出管理Service
 */
public interface UmsShowService
{
    /**
     * 添加演出
     *
     * @param param
     */
    int create(UmsShowParam param);
}
