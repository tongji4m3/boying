package com.tongji.boying.service;


import com.tongji.boying.dto.UmsUserParam;
import com.tongji.boying.model.BoyingUser;

import java.util.List;

public interface UmsUserService
{
    List<BoyingUser> list();


    int update(Integer id, UmsUserParam param);


    int delete(Integer id);

    int updateStatus(Integer id, Boolean status);
}
