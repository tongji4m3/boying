package com.tongji.boying.service;

import com.tongji.boying.dto.FrequentParam;
import com.tongji.boying.model.Frequent;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserFrequentService
{
    /**
     * 添加常用联系人
     */
    int add(FrequentParam param);

    /**
     * 删除常用联系人
     * @param id 常用联系人表的id
     */
    int delete(int id);

    /**
     * 修改常用联系人
     * @param id 常用联系人表的id
     */
    @Transactional
    int update(int id, FrequentParam param);

    /**
     * 返回当前用户的常用联系人
     * @param pageNum
     * @param pageSize
     */
    List<Frequent> list(Integer pageNum, Integer pageSize);

    /**
     * 获取常用联系人详情
     * @param id 常用联系人id
     */
    Frequent getItem(int id);

    /**
     * 设为默认常用联系人
     */
    void setDefault(int id);

    /**
     * 获取默认常用联系人
     */
    Frequent getDefault();
}
