package com.tongji.boying.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.dto.userParam.BoyingUserReturn;
import com.tongji.boying.dto.userParam.GetUserByNameParam;
import com.tongji.boying.dto.userParam.NumsUserParam;
import com.tongji.boying.dto.userParam.UserListParam;
import com.tongji.boying.mapper.BoyingUserMapper;
import com.tongji.boying.model.BoyingUser;
import com.tongji.boying.model.BoyingUserExample;
import com.tongji.boying.service.NumsUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

@Service
public class NumsUserServiceImpl implements NumsUserService {

    @Autowired
    private BoyingUserMapper userMapper;

    /**
     * 用于密码加密
     */
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${redis.database}")
    private String REDIS_DATABASE;
    //    用户
    @Value("${redis.key.user}")
    private String REDIS_KEY_USER;


    @Override
    public BoyingUserReturn getUserById(Integer id) {
        BoyingUser boyingUser = userMapper.selectByPrimaryKey(id);
        if (boyingUser == null) Asserts.fail("该用户不存在！");

        BoyingUserReturn boyingUserReturn = new BoyingUserReturn();

        BeanUtils.copyProperties(boyingUser, boyingUserReturn);
        if (boyingUser.getAdminDelete() == 1) {
            boyingUserReturn.setAdminDelete(true);
        }
        else {
            boyingUserReturn.setAdminDelete(false);
        }
        if (boyingUser.getGender() == 1) {
            boyingUserReturn.setGender(true);
        }
        else {
            boyingUserReturn.setGender(false);
        }

        return boyingUserReturn;
    }

    @Override
    public List<BoyingUser> getUserByName(GetUserByNameParam param) {
        Integer pageNum = param.getPageNum();
        if (pageNum == null || pageNum == 0) pageNum = 1;
        Integer pageSize = param.getPageSize();
        if (pageSize == null || pageSize == 0) pageSize = 5;

        BoyingUserExample boyingUserExample = new BoyingUserExample();
        BoyingUserExample.Criteria criteria = boyingUserExample.createCriteria();

        String username = param.getUsername();
        if (!StrUtil.isEmpty(username)) {
            criteria.andUsernameLike("%" + username + "%");
        }
        boyingUserExample.setOrderByClause("create_time desc");

        //开启分页
        PageHelper.startPage(pageNum, pageSize);
        List<BoyingUser> boyingUsers = userMapper.selectByExample(boyingUserExample);
        if (boyingUsers == null || boyingUsers.isEmpty()) Asserts.fail("该用户不存在！");


        return boyingUsers;
//        List<BoyingUserReturn> boyingUserReturnList = new LinkedList<>();
//        for (BoyingUser boyingUser : boyingUsers) {
//            BoyingUserReturn boyingUserReturn = new BoyingUserReturn();
//            BeanUtils.copyProperties(boyingUser, boyingUserReturn);
//            if (boyingUser.getAdminDelete() == 1) {
//                boyingUserReturn.setAdminDelete(true);
//            }
//            else {
//                boyingUserReturn.setAdminDelete(false);
//            }
//            if (boyingUser.getGender() == 1) {
//                boyingUserReturn.setGender(true);
//            }
//            else {
//                boyingUserReturn.setGender(false);
//            }
//            boyingUserReturnList.add(boyingUserReturn);
//        }
//        return boyingUserReturnList;
    }

    @Override
    public List<BoyingUser> listAllUsers(UserListParam param) {
        Integer pageNum = param.getPageNum();
        if (pageNum == null || pageNum == 0) pageNum = 1;
        Integer pageSize = param.getPageSize();
        if (pageSize == null || pageSize == 0) pageSize = 5;
        BoyingUserExample boyingUserExample = new BoyingUserExample();

        boyingUserExample.setOrderByClause("create_time desc");
        //开启分页
        PageHelper.startPage(pageNum, pageSize);
        List<BoyingUser> boyingUsers = userMapper.selectByExample(boyingUserExample);
        if (ObjectUtil.isEmpty(boyingUsers)) Asserts.fail("不存在任何用户");

        return boyingUsers;

        /*List<BoyingUserReturn> boyingUserReturnList = new LinkedList<>();
        for (BoyingUser boyingUser : boyingUsers) {
            BoyingUserReturn boyingUserReturn = new BoyingUserReturn();
            BeanUtils.copyProperties(boyingUser, boyingUserReturn);
            if (boyingUser.getAdminDelete() == 1) {
                boyingUserReturn.setAdminDelete(true);
            }
            else {
                boyingUserReturn.setAdminDelete(false);
            }
            if (boyingUser.getGender() == 1) {
                boyingUserReturn.setGender(true);
            }
            else {
                boyingUserReturn.setGender(false);
            }
            boyingUserReturnList.add(boyingUserReturn);
        }
        return boyingUserReturnList;*/
    }

    @Override
    public void ChangeUserStatusById(Integer id) {
        BoyingUser user = userMapper.selectByPrimaryKey(id);
        if (user == null) Asserts.fail("用户不存在！");
        //切换用户状态
        if (user.getAdminDelete() == 1) {
            user.setAdminDelete(0);
        }
        else {
            user.setAdminDelete(1);
        }


        //删除用户的缓存
        String key = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + user.getUsername();
        String key2 = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + user.getPhone();
        redisTemplate.delete(key);
        redisTemplate.delete(key2);

        int count = userMapper.updateByPrimaryKey(user);
        if (count == 0) Asserts.fail("修改用户状态失败！");
    }

    @Override
    public void add(NumsUserParam param) {
        //查询是否已有该用户
        BoyingUserExample example = new BoyingUserExample();
        //用户名,手机号唯一
        example.createCriteria().andUsernameEqualTo(param.getUsername());
        example.or(example.createCriteria().andPhoneEqualTo(param.getPhone()));
        List<BoyingUser> users = userMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(users)) {
            Asserts.fail("该用户已经存在或手机号已注册");
        }

        BoyingUser user = new BoyingUser();
        BeanUtils.copyProperties(param, user);

        if (param.getGender() != null) {
            if (param.getGender()) {
                user.setGender(1);
            }
            else {
                user.setGender(0);
            }
        }
        user.setAdminDelete(0);

        user.setPassword(passwordEncoder.encode(param.getPassword()));//存储加密后的
        int count = userMapper.insertSelective(user);
        if (count == 0) Asserts.fail("添加用户失败！");
    }
}
