package com.tongji.boying.service.impl;

import com.github.pagehelper.PageHelper;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.UserFrequentParam;
import com.tongji.boying.mapper.FrequentMapper;
import com.tongji.boying.model.Address;
import com.tongji.boying.model.Frequent;
import com.tongji.boying.model.FrequentExample;
import com.tongji.boying.model.User;
import com.tongji.boying.service.UserFrequentService;
import com.tongji.boying.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserFrequentServiceImpl implements UserFrequentService {
    @Autowired
    private UserService userService;
    @Autowired
    private FrequentMapper frequentMapper;


    @Override
    public int add(UserFrequentParam param) {
        User user = userService.getCurrentUser();
        Frequent frequent = new Frequent();
        BeanUtils.copyProperties(param, frequent);
        frequent.setUserId(user.getUserId());
        return frequentMapper.insert(frequent);
    }

    @Override
    public int delete(int id) {
        User user = userService.getCurrentUser();
        FrequentExample frequentExample = new FrequentExample();
        //只能删除本人的对应的常用联系人
        frequentExample.createCriteria().andUserIdEqualTo(user.getUserId()).andFrequentIdEqualTo(id);
        return frequentMapper.deleteByExample(frequentExample);
    }

    @Override
    public int update(int id, UserFrequentParam param) {
        //得到了对应的Id
        User user = userService.getCurrentUser();

        //判断信息是否存在
        FrequentExample frequentExample = new FrequentExample();
        frequentExample.createCriteria().andUserIdEqualTo(user.getUserId()).andFrequentIdEqualTo(id);
        List<Frequent> frequents = frequentMapper.selectByExample(frequentExample);
        if (frequents.size() == 0) Asserts.fail("要更新的常用联系人不存在!");

        //传入字段,并更新
        Frequent frequent = new Frequent();
        BeanUtils.copyProperties(param, frequent);
        frequent.setUserId(user.getUserId());
        frequent.setFrequentId(id);
        return frequentMapper.updateByPrimaryKeySelective(frequent);
    }

    @Override
    public List<Frequent> list(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);//分页相关
        User user = userService.getCurrentUser();
        FrequentExample frequentExample = new FrequentExample();
        frequentExample.createCriteria().andUserIdEqualTo(user.getUserId());
        return frequentMapper.selectByExample(frequentExample);
    }

    @Override
    public Frequent getItem(int id) {
        User user = userService.getCurrentUser();
        FrequentExample frequentExample = new FrequentExample();
        frequentExample.createCriteria().andUserIdEqualTo(user.getUserId()).andFrequentIdEqualTo(id);
        List<Frequent> frequents = frequentMapper.selectByExample(frequentExample);

        if (!CollectionUtils.isEmpty(frequents)) {
            return frequents.get(0);
        }
        return null;
    }

    @Override
    public Frequent getDefault() {
        User user = userService.getCurrentUser();
        Integer defaultFrequent = user.getDefaultFrequent();
        if (defaultFrequent == null) Asserts.fail("无常用联系人");
        return frequentMapper.selectByPrimaryKey(defaultFrequent);
    }

    @Override
    public void setDefault(int id) {
        Frequent item = getItem(id);
        if (item == null) Asserts.fail("要设为默认的常用联系人不存在!");
        userService.setDefaultFrequent(id);
    }

    @Override
    public void setNullFrequent() {
        userService.setDefaultFrequent(null);
    }

}
