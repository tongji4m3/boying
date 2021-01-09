package com.tongji.boying.service;

import com.tongji.boying.dto.NumsUserParam;
import com.tongji.boying.dto.userParam.GetUserByNameParam;
import com.tongji.boying.model.BoyingUser;
import com.tongji.boying.model.BoyingOrder;

import java.util.List;

public interface NumsUserService {
    /**
     * 删除普通用户        //TODO:删除普通用户还没清用户缓存
     */
    int delete(Integer id);

    /**
     * 根据id获取用户信息
     */
    BoyingUser getUserById(Integer id);


    List<BoyingUser> getUserByName(GetUserByNameParam param);

    /**
     * 获取所有用户信息
     */
    List<BoyingUser> listAllUsers();

    /**
     * 根据id设置用户启用状态为启用
     */
    void ChangeUserStatusById(Integer id);

    /**
     * 列出所有用户订单
     */
    List<BoyingOrder> listOrders();

    /**
     * 删除某id订单
     */
    int deleteOrder(Integer id);

    /**
     * 更新某id用户信息
     */
    int update(Integer id, NumsUserParam param);

    /**
     * 添加新用户
     */
    void add(NumsUserParam param);
}
