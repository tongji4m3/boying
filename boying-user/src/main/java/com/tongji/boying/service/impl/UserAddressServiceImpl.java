package com.tongji.boying.service.impl;

import com.github.pagehelper.PageHelper;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.AddressParam;
import com.tongji.boying.mapper.AddressMapper;
import com.tongji.boying.model.*;
import com.tongji.boying.service.UserAddressService;
import com.tongji.boying.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserAddressServiceImpl implements UserAddressService
{
    @Autowired
    private UserService userService;
    @Autowired
    private AddressMapper addressMapper;


    @Override
    public int add(AddressParam param)
    {
        User user = userService.getCurrentUser();
        Address address = new Address();
        BeanUtils.copyProperties(param, address);
        address.setUserId(user.getUserId());
        return addressMapper.insert(address);
    }

    @Override
    public int delete(int id)
    {
        User user = userService.getCurrentUser();
        AddressExample addressExample = new AddressExample();
        //只能删除本人的对应的收货地址
        addressExample.createCriteria().andUserIdEqualTo(user.getUserId()).andAddressIdEqualTo(id);
        return addressMapper.deleteByExample(addressExample);
    }

    @Override
    public int update(int id, AddressParam param)
    {
        //得到了对应的Id
        User user = userService.getCurrentUser();

        //判断信息是否存在
        AddressExample addressExample = new AddressExample();
        addressExample.createCriteria().andUserIdEqualTo(user.getUserId()).andAddressIdEqualTo(id);
        List<Address> addressList = addressMapper.selectByExample(addressExample);
        if(addressList.size()==0) Asserts.fail("要更新的收货地址不存在!");

        //传入字段,并更新
        Address address = new Address();
        BeanUtils.copyProperties(param, address);
        address.setUserId(user.getUserId());
        address.setAddressId(id);
        return addressMapper.updateByPrimaryKeySelective(address);
    }

    @Override
    public List<Address> list(Integer pageNum, Integer pageSize)
    {
        PageHelper.startPage(pageNum, pageSize);//分页相关
        User user = userService.getCurrentUser();
        AddressExample addressExample = new AddressExample();
        addressExample.createCriteria().andUserIdEqualTo(user.getUserId());
        return addressMapper.selectByExample(addressExample);
    }

    @Override
    public Address getItem(int id)
    {
        User user = userService.getCurrentUser();
        AddressExample addressExample = new AddressExample();
        addressExample.createCriteria().andUserIdEqualTo(user.getUserId()).andAddressIdEqualTo(id);
        List<Address> address = addressMapper.selectByExample(addressExample);

        if (!CollectionUtils.isEmpty(address))
        {
            return address.get(0);
        }
        return null;
    }

    @Override
    public void setDefault(int id)
    {
        Address item = getItem(id);
        if(item==null) Asserts.fail("要设为默认的收货地址不存在!");
        User user = userService.getCurrentUser();
        user.setDefaultAddress(id);
    }

    @Override
    public Address getDefault()
    {
        User user = userService.getCurrentUser();
        Integer defaultAddress = user.getDefaultAddress();
        if(defaultAddress==null) Asserts.fail("无收货地址");
        AddressExample addressExample = new AddressExample();
        addressExample.createCriteria().andAddressIdEqualTo(defaultAddress);
        List<Address> address = addressMapper.selectByExample(addressExample);
//        说明删除了收货地址,但是没在用户名进行更新
        if(address.isEmpty())
        {
//            在这里对用户表进行延迟更新
            userService.setDefaultAddress(null);
            Asserts.fail("无收货地址");
        }
        return null;
    }
}
