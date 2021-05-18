package com.tongji.boying.service;

/**
 * @author cxw
 * @Description 完成
 * @date 2021年5月18日10:58:50
 */

import com.tongji.boying.dto.UmsPromoParam;
import com.tongji.boying.model.BoyingPromo;

import java.util.Date;
import java.util.List;

/**
 * 后台活动管理
 */
public interface UmsPromoService {
    /**
     * 查看活动
     */
    List<BoyingPromo> list();

    /**
     * 查看某个活动
     */
    BoyingPromo getPromo(Integer id);

    /**
     * 添加活动
     * @param param
     */
    int create(UmsPromoParam param);

    /**
     * 修改活动
     */
    int update(Integer id,UmsPromoParam param);

    /**
     * 删除活动
     */
    int delete(Integer id);

    /**
     * 批量删除活动
     */
    int delete(List<Integer> ids);
}
