package com.tongji.boying.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.userParam.NumsUserParam;
import com.tongji.boying.dto.userParam.GetUserByNameParam;
import com.tongji.boying.mapper.BoyingOrderMapper;
import com.tongji.boying.mapper.BoyingUserMapper;
import com.tongji.boying.model.BoyingOrder;
import com.tongji.boying.model.BoyingOrderExample;
import com.tongji.boying.model.BoyingUser;
import com.tongji.boying.model.BoyingUserExample;
import com.tongji.boying.service.NumsUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NumsUserServiceImpl implements NumsUserService {

    @Autowired
    private BoyingUserMapper userMapper;


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${redis.database}")
    private String REDIS_DATABASE;
    //    用户
    @Value("${redis.key.user}")
    private String REDIS_KEY_USER;


    @Override
    public BoyingUser getUserById(Integer id) {
        BoyingUser boyingUser = userMapper.selectByPrimaryKey(id);
        if (boyingUser == null) Asserts.fail("该用户不存在！");
        return boyingUser;
    }

    @Override
    public List<BoyingUser> getUserByName(GetUserByNameParam param) {
        Integer pageNum = param.getPageNum();
        if (pageNum == null || pageNum == 0) pageNum = 0;
        Integer pageSize = param.getPageSize();
        if (pageSize == null || pageSize == 0) pageSize = 5;

        String username = param.getUsername();
        if (username == null) username = "";

        BoyingUserExample boyingUserExample = new BoyingUserExample();
        boyingUserExample.createCriteria().andUsernameLike("%" + username + "%");
        List<BoyingUser> boyingUsers = userMapper.selectByExample(boyingUserExample);
        if (boyingUsers == null || boyingUsers.isEmpty()) Asserts.fail("该用户不存在！");
        return boyingUsers;
    }

    @Override
    public List<BoyingUser> listAllUsers() {
        List<BoyingUser> boyingUsers = userMapper.selectByExample(new BoyingUserExample());
        if (ObjectUtil.isEmpty(boyingUsers)) Asserts.fail("不存在任何用户");
        return boyingUsers;
    }

    @Override
    public void ChangeUserStatusById(Integer id) {
        BoyingUser user = userMapper.selectByPrimaryKey(id);
        if (user == null) Asserts.fail("用户不存在！");
        //切换用户状态
        user.setAdminDelete(!user.getAdminDelete());

        //删除用户的缓存
        String key = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + user.getUsername();
        String key2 = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + user.getPhone();
        redisTemplate.delete(key);
        redisTemplate.delete(key2);

        userMapper.updateByPrimaryKey(user);
    }
    @Override
    public void add(NumsUserParam param) {
        BoyingUser user = new BoyingUser();
        BeanUtils.copyProperties(param, user);
        int count = userMapper.insertSelective(user);
        if (count == 0) {
            Asserts.fail("添加用户失败！");
        }
    }
}
