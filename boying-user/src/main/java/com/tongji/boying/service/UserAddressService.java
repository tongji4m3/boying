package com.tongji.boying.service;

import com.tongji.boying.model.Address;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserAddressService
{
    /**
     * 添加收货地址
     */
    int add(Address address);

    /**
     * 删除收货地址
     * @param id 地址表的id
     */
    int delete(int id);

    /**
     * 修改收货地址
     * @param id 地址表的id
     * @param address 修改的收货地址信息
     */
    @Transactional
    int update(int id, Address address);

    /**
     * 返回当前用户的收货地址
     */
    List<Address> list();

    /**
     * 获取地址详情
     * @param id 地址id
     */
    Address getItem(int id);

    /**
     * 将默认收货地址都变为非默认地址
     */
    void changeDefault();
}
