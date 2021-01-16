package com.tongji.boying.service;

import com.tongji.boying.dto.userParam.BoyingUserReturn;
import com.tongji.boying.dto.userParam.NumsUserParam;
import com.tongji.boying.dto.userParam.GetUserByNameParam;
import com.tongji.boying.dto.userParam.UserListParam;

import java.util.List;

public interface NumsUserService {
    /**
     * 根据id获取用户信息
     */
    BoyingUserReturn getUserById(Integer id);


    List<BoyingUserReturn> getUserByName(GetUserByNameParam param);

    /**
     * 获取所有用户信息
     * @param param
     */
    List<BoyingUserReturn> listAllUsers(UserListParam param);

    /**
     * 根据id设置用户启用状态为启用
     */
    void ChangeUserStatusById(Integer id);


    /**
     * 添加新用户
     */
    void add(NumsUserParam param);
}
