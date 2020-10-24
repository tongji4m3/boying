package com.tongji.boying.service.impl;

import com.tongji.boying.mapper.FrequentBuyersMapper;
import com.tongji.boying.mapper.FrequentBuyersMapper;
import com.tongji.boying.model.FrequentBuyers;
import com.tongji.boying.model.FrequentBuyersExample;
import com.tongji.boying.model.User;
import com.tongji.boying.service.UserFrequentBuyersService;
import com.tongji.boying.service.UserFrequentBuyersService;
import com.tongji.boying.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserFrequentBuyersServiceImpl implements UserFrequentBuyersService
{
    @Autowired
    private UserService userService;
    @Autowired
    private FrequentBuyersMapper frequentBuyersMapper;


    @Override
    public int add(FrequentBuyers frequentBuyers)
    {
        User user = userService.getCurrentUser();
        frequentBuyers.setUserId(user.getUserId());
        //        要设置为默认常用联系人,则将默认常用联系人抹除
        //        不能篡改ID
        frequentBuyers.setFrequentId(null);
        if(frequentBuyers.getDefaultBuyer())
        {
            changeDefault();
        }
        return frequentBuyersMapper.insert(frequentBuyers);
    }

    @Override
    public int delete(int id)
    {
        User user = userService.getCurrentUser();
        FrequentBuyersExample frequentBuyersExample = new FrequentBuyersExample();
        //只能删除本人的对应的常用联系人
        frequentBuyersExample.createCriteria().andUserIdEqualTo(user.getUserId()).andFrequentIdEqualTo(id);
        return frequentBuyersMapper.deleteByExample(frequentBuyersExample);
    }

    @Override
    public int update(int id, FrequentBuyers frequentBuyers)
    {
        //得到了对应的Id
        User user = userService.getCurrentUser();
        FrequentBuyersExample frequentBuyersExample = new FrequentBuyersExample();
        frequentBuyersExample.createCriteria().andUserIdEqualTo(user.getUserId()).andFrequentIdEqualTo(id);

        //不能自己给别人增加常用联系人
        frequentBuyers.setUserId(user.getUserId());
//        不能篡改ID
        frequentBuyers.setFrequentId(null);
//        要设置为默认常用联系人,则将默认常用联系人抹除
        if(frequentBuyers.getDefaultBuyer())
        {
            changeDefault();
        }
        return frequentBuyersMapper.updateByExampleSelective(frequentBuyers,frequentBuyersExample);
    }

    @Override
    public List<FrequentBuyers> list()
    {
        User user = userService.getCurrentUser();
        FrequentBuyersExample frequentBuyersExample = new FrequentBuyersExample();
        frequentBuyersExample.createCriteria().andUserIdEqualTo(user.getUserId());
        return frequentBuyersMapper.selectByExample(frequentBuyersExample);
    }

    @Override
    public FrequentBuyers getItem(int id)
    {
        User user = userService.getCurrentUser();
        FrequentBuyersExample frequentBuyersExample = new FrequentBuyersExample();
        frequentBuyersExample.createCriteria().andUserIdEqualTo(user.getUserId()).andFrequentIdEqualTo(id);
        List<FrequentBuyers> frequentBuyerses = frequentBuyersMapper.selectByExample(frequentBuyersExample);

        if (!CollectionUtils.isEmpty(frequentBuyerses))
        {
            return frequentBuyerses.get(0);
        }
        return null;
    }

    @Override
    public void changeDefault()
    {
        User user = userService.getCurrentUser();
        FrequentBuyersExample frequentBuyersExample = new FrequentBuyersExample();
//        找到默认常用联系人
        frequentBuyersExample.createCriteria().andUserIdEqualTo(user.getUserId()).andDefaultBuyerEqualTo(true);
        List<FrequentBuyers> frequentBuyerses = frequentBuyersMapper.selectByExample(frequentBuyersExample);
//        默认常用联系人要么无,要么只有一个
        if(frequentBuyerses.size()==1)
        {
            frequentBuyerses.get(0).setDefaultBuyer(false);
            frequentBuyersMapper.updateByExampleSelective(frequentBuyerses.get(0),frequentBuyersExample);
        }

    }
}
