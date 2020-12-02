package com.tongji.boying.service;

import com.tongji.boying.model.User;

import java.util.List;

public interface NumsUserService {
    /**
     * 删除普通用户        //TODO:删除普通用户还没清用户缓存
     */
    int delete(Integer id);

    /**
     * 根据id获取用户信息   //TODO:有时间可以改成关键字搜索
     */
    User getUserById(Integer id);

    /**
     * 根据id设置用户启用状态为启用
     */
    int ChangeUserStatusById(Integer id);

}
