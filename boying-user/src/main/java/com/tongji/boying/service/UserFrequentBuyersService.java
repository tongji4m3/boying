package com.tongji.boying.service;

import com.tongji.boying.model.FrequentBuyers;
import com.tongji.boying.model.FrequentBuyers;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserFrequentBuyersService
{
    /**
     * 添加常用联系人
     */
    int add(FrequentBuyers frequentBuyers);

    /**
     * 删除常用联系人
     * @param id 常用联系人表的id
     */
    int delete(int id);

    /**
     * 修改常用联系人
     * @param id 常用联系人表的id
     * @param frequentBuyers 修改的常用联系人信息
     */
    @Transactional
    int update(int id, FrequentBuyers frequentBuyers);

    /**
     * 返回当前用户的常用联系人
     */
    List<FrequentBuyers> list();

    /**
     * 获取常用联系人详情
     * @param id 常用联系人id
     */
    FrequentBuyers getItem(int id);

    /**
     * 将默认常用联系人都变为非默认常用联系人
     */
    void changeDefault();
}
