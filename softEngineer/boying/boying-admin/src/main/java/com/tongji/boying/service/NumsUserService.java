package com.tongji.boying.service;

import com.tongji.boying.dto.userParam.NumsUserParam;
import com.tongji.boying.dto.userParam.GetUserByNameParam;
import com.tongji.boying.dto.userParam.UserListParam;
import com.tongji.boying.model.BoyingUser;

import java.util.List;

public interface NumsUserService {
    /**
     * 根据id获取用户信息
     */
    BoyingUser getUserById(Integer id);


    List<BoyingUser> getUserByName(GetUserByNameParam param);

    /**
     * 获取所有用户信息
     * @param param
     */
    List<BoyingUser> listAllUsers(UserListParam param);

    /**
     * 根据id设置用户启用状态为启用
     */
    void ChangeUserStatusById(Integer id);


    /**
     * 添加新用户
     */
    void add(NumsUserParam param);
}
