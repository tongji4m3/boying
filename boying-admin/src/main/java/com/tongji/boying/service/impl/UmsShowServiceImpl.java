package com.tongji.boying.service.impl;

import com.tongji.boying.dto.UmsShowParam;
import com.tongji.boying.mapper.RoleMapper;
import com.tongji.boying.mapper.ShowMapper;
import com.tongji.boying.model.Show;
import com.tongji.boying.service.UmsShowService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cheng fu
 * @Description TODO
 * @date 2020/11/1-21:34
 */
@Service
public class UmsShowServiceImpl implements UmsShowService
{
    @Autowired
    private ShowMapper showMapper;
    @Override
    public int create(UmsShowParam param)
    {
        Show show=new Show();
        BeanUtils.copyProperties(param, show);
        System.out.println(param.toString());
        System.out.println(show.toString());
        return showMapper.insertSelective(show);
    }
}
