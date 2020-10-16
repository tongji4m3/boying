package com.tongji.boying.user.service.impl;

import com.tongji.boying.mbg.mapper.AdminMapper;
import com.tongji.boying.mbg.model.Admin;
import com.tongji.boying.user.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminServiceImpl implements AdminService
{
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void getAdmin(Integer id)
    {
        Admin admin = adminMapper.selectByPrimaryKey(id);
        System.out.println(admin);
    }
}
