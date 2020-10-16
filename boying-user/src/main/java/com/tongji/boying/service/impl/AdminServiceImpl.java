package com.tongji.boying.service.impl;

import com.tongji.boying.mapper.AdminMapper;
import com.tongji.boying.model.Admin;
import com.tongji.boying.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService
{
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void getAdmin(Integer id)
    {
        Admin admin = adminMapper.selectByPrimaryKey(50);
        System.out.println(admin);
    }
}
