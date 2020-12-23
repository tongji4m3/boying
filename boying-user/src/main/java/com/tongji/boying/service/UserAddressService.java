package com.tongji.boying.service;

import com.tongji.boying.dto.UserAddressParam;
import com.tongji.boying.model.Address;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserAddressService
{
    /**
     * 添加收货地址
     */
    int add(UserAddressParam param);

    /**
     * 删除收货地址
     *
     * @param id 地址表的id
     */
    int delete(int id);

    /**
     * 修改收货地址
     *
     * @param id 地址表的id
     */
    @Transactional
    int update(int id, UserAddressParam param);

    /**
     * 返回当前用户的收货地址
     *
     * @param pageNum
     * @param pageSize
     */
    List<Address> list(Integer pageNum, Integer pageSize);

    /**
     * 获取地址详情
     *
     * @param id 地址id
     */
    Address getItem(int id);

    /**
     * 获取收货地址
     */
    Address getDefault();

    /**
     * 设为收货地址
     */
    void setDefault(int id);

    /**
    取消绑定收货地址
     */
    void setNullAddress();
}
