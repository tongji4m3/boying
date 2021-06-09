package com.tongji.boying.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.SmsShowParam;
import com.tongji.boying.mapper.BoyingShowMapper;
import com.tongji.boying.model.AdminMenu;
import com.tongji.boying.model.BoyingShow;
import com.tongji.boying.model.BoyingShowExample;
//import com.tongji.boying.model.Role;
//import com.tongji.boying.model.RoleExample;
import com.tongji.boying.service.SmsShowService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 后台演出管理Service实现类
 */
@Service
public class SmsShowServiceImpl implements SmsShowService
{
    @Autowired
    private BoyingShowMapper boyingShowMapper;

    @Override
    public List<BoyingShow> list()
    {
        return boyingShowMapper.selectByExample(new BoyingShowExample());
    }

    @Override
    public BoyingShow getShow(Integer id)
    {
        return boyingShowMapper.selectByPrimaryKey(id);
    }

    @Override
    public int create(SmsShowParam param)
    {
        System.out.println(param);
        checkBoyingShowParam(param, -1);
        BoyingShow show = new BoyingShow();
        BeanUtils.copyProperties(param, show);
        System.out.println(show);
        //这里不懂为啥要手动设置，直接copyProperties日期没法copy
        show.setStartTime(param.getDayStart());
        show.setEndTime(param.getDayEnd());
        System.out.println(show);

        return boyingShowMapper.insertSelective(show);
    }

    @Override
    public int update(Integer id, SmsShowParam param)
    {
        checkBoyingShowParam(param, id);
        BoyingShow show = new BoyingShow();

        BeanUtils.copyProperties(param, show);
        System.out.println(show);
        //这里不懂为啥要手动设置，直接copyProperties日期没法copy
        show.setStartTime(param.getDayStart());
        show.setEndTime(param.getDayEnd());
        System.out.println(show);
        show.setId(id);
        return boyingShowMapper.updateByPrimaryKeySelective(show);

    }

    @Override
    public int delete(List<Integer> ids)
    {
        BoyingShowExample example = new BoyingShowExample();
        example.createCriteria().andIdIn(ids);
        if (boyingShowMapper.selectByExample(example).size() != ids.size())
        {
            Asserts.fail("某些演出Id不存在!");
        }
        return boyingShowMapper.deleteByExample(example);
    }

    @Override
    public int delete(Integer id)
    {
        return boyingShowMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateStatus(Integer id, Boolean status)
    {
        BoyingShow boyingShow = new BoyingShow();
        boyingShow.setId(id);
        boyingShow.setAdminDelete(status);
        return boyingShowMapper.updateByPrimaryKeySelective(boyingShow);
    }

    private void checkBoyingShowParam(SmsShowParam param, Integer id)
    {
        BoyingShowExample boyingShowExample = new BoyingShowExample();
        BoyingShowExample.Criteria criteria = boyingShowExample.createCriteria();
        criteria.andNameEqualTo(param.getName());
        if (id != -1)
        {
            criteria.andIdNotEqualTo(id);
        }
        List<BoyingShow> shows = boyingShowMapper.selectByExample(boyingShowExample);
        if (CollUtil.isNotEmpty(shows))
        {
            Asserts.fail("演出名称不能重复!");
        }
    }
}
