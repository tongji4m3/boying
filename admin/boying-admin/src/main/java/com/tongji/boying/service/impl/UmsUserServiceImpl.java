package com.tongji.boying.service.impl;


import com.tongji.boying.common.service.RedisService;
import com.tongji.boying.dto.UmsUserParam;
import com.tongji.boying.mapper.BoyingUserMapper;
import com.tongji.boying.model.AdminRole;
import com.tongji.boying.model.BoyingUser;
import com.tongji.boying.model.BoyingUserExample;
import com.tongji.boying.service.UmsUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmsUserServiceImpl implements UmsUserService
{
    @Autowired
    private BoyingUserMapper boyingUserMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RedisService redisService;
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.key.user}")
    private String REDIS_KEY_USER;
    @Override
    public List<BoyingUser> list()
    {
        return boyingUserMapper.selectByExample(new BoyingUserExample());
    }

    @Override
    public int update(Integer id, UmsUserParam param)
    {
        BoyingUser boyingUser = new BoyingUser();
        BeanUtils.copyProperties(param, boyingUser);
        boyingUser.setId(id);
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(boyingUser.getPassword());
        System.out.println(boyingUser.getPassword());
        System.out.println(encodePassword);
        boyingUser.setPassword(encodePassword);
        return boyingUserMapper.updateByPrimaryKeySelective(boyingUser);
    }

    @Override
    public int delete(Integer id)
    {
        return boyingUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateStatus(Integer id, Boolean adminDelete)
    {
        BoyingUser boyingUser = new BoyingUser();
        boyingUser.setAdminDelete(adminDelete);
        boyingUser.setId(id);
        System.out.println(adminDelete);
        delUser(boyingUser.getId());
        return boyingUserMapper.updateByPrimaryKeySelective(boyingUser);
    }

    private void delUser(int userId) {
        BoyingUser user = boyingUserMapper.selectByPrimaryKey(userId);
        if (user != null) {
            String key = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + user.getUsername();
            String key2 = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + user.getPhone();
            redisService.del(key);
            redisService.del(key2);
        }
    }
}
