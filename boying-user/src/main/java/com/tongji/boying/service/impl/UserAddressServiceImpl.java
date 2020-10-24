package com.tongji.boying.service.impl;

import com.tongji.boying.mapper.AddressMapper;
import com.tongji.boying.model.Address;
import com.tongji.boying.model.AddressExample;
import com.tongji.boying.model.User;
import com.tongji.boying.service.UserAddressService;
import com.tongji.boying.service.UserService;
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
    public int add(Address address)
    {
        User user = userService.getCurrentUser();
        address.setUserId(user.getUserId());
        //        要设置为默认地址,则将默认地址抹除
        if(address.getDefaultAddress())
        {
            changeDefault();
        }
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
    public int update(int id, Address address)
    {
        //得到了对应的Id
        User user = userService.getCurrentUser();
        AddressExample addressExample = new AddressExample();
        addressExample.createCriteria().andUserIdEqualTo(user.getUserId()).andAddressIdEqualTo(id);

        //不能自己给别人增加收货地址
        address.setUserId(user.getUserId());
//        要设置为默认地址,则将默认地址抹除
        if(address.getDefaultAddress())
        {
            changeDefault();
        }
        return addressMapper.updateByExampleSelective(address,addressExample);
    }

    @Override
    public List<Address> list()
    {
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
        List<Address> addresses = addressMapper.selectByExample(addressExample);

        if (!CollectionUtils.isEmpty(addresses))
        {
            return addresses.get(0);
        }
        return null;
    }

    @Override
    public void changeDefault()
    {
        User user = userService.getCurrentUser();
        AddressExample addressExample = new AddressExample();
//        找到默认地址
        addressExample.createCriteria().andUserIdEqualTo(user.getUserId()).andDefaultAddressEqualTo(true);
        List<Address> addresses = addressMapper.selectByExample(addressExample);
//        默认地址要么无,要么只有一个
        if(addresses.size()==1)
        {
            addresses.get(0).setDefaultAddress(false);
            addressMapper.updateByExampleSelective(addresses.get(0),addressExample);
        }

    }
}
