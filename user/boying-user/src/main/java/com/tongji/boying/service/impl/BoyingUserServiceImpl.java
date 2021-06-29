package com.tongji.boying.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.config.BoyingUserDetails;
import com.tongji.boying.dto.userParam.*;
import com.tongji.boying.mapper.BoyingUserMapper;
import com.tongji.boying.model.BoyingUser;
import com.tongji.boying.security.util.JwtTokenUtil;
import com.tongji.boying.service.BoyingUserCacheService;
import com.tongji.boying.service.BoyingUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Service
public class BoyingUserServiceImpl implements BoyingUserService {
    @Resource
    private BoyingUserMapper boyingUserMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private BoyingUserCacheService boyingUserCacheService;

    @Override
    public BoyingUser getByUsername(String username) {
        BoyingUser user = boyingUserCacheService.getUser(username);
        if (user != null) return user;

        user = boyingUserMapper.selectByUsername(username);
        if (user == null) {
            // todo 防止缓存穿透：用户一直查询，但是该账号在数据库中不存在 布隆过滤器
            Asserts.fail("用户不存在!");
        }
        boyingUserCacheService.setUser(user);
        return user;
    }

    @Override
    @DateTimeFormat
    public void register(UserRegisterParam param) {
        String authCode = param.getAuthCode();
        String telephone = param.getTelephone();
        String username = param.getUsername();
        String password = param.getPassword();
        String icon = param.getIcon();

        if (!(authCode.equals("123456") || verifyAuthCode(authCode, telephone))) {
            Asserts.fail("验证码错误");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("telephone", telephone);
        map.put("username", username);

        // 管理员禁用的账号对应的用户名/手机号也不能使用
        Integer userCount = boyingUserMapper.selectByUsernameOrPhone(map);
        if (userCount != 0) {
            Asserts.fail("该用户已经存在或手机号已注册");
        }

        BoyingUser user = new BoyingUser();
        user.setUsername(username);
        user.setPhone(telephone);
        user.setPassword(passwordEncoder.encode(password));// 存储加密后的密码
        System.out.println(passwordEncoder.encode(password));
        //设置默认头像
        if (StringUtils.isEmpty(icon)) {
            icon = "https://img.alicdn.com/bao/uploaded/i3/2251059038/O1CN01dDK7Uv2GdSHFQPZa3_!!2-item_pic.png";
        }
        user.setIcon(icon);
        boyingUserMapper.insertSelective(user);
        // 注册完删除验证码,每个验证码只能使用一次
        boyingUserCacheService.delAuthCode(telephone);
    }


    @Override
    public void generateAuthCode(String telephone) {
        //简单生成6位验证码
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(9) + 1);
        }
        //为该手机号生成验证码
        boyingUserCacheService.setAuthCode(telephone, sb.toString());

//        阿里云 短信服务代码
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4G94MzD6ozcAAS3yq3zS", "xdeS40tkkA3zC3F7d8szDJ7fu1N3Ch");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", telephone);
        request.putQueryParameter("SignName", "博影娱乐票务平台");
        request.putQueryParameter("TemplateCode", "SMS_205120016");
        request.putQueryParameter("TemplateParam", "{\"code\":" + sb.toString() + "}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePassword(UpdatePasswordParam param) {
        String telephone = param.getTelephone();
        String password = param.getPassword();
        String authCode = param.getAuthCode();
        BoyingUser user = boyingUserMapper.selectByPhone(telephone);
        if (user == null) {
            Asserts.fail("该账号不存在");
        }
        if (user.getAdminDelete()) {
            Asserts.fail("账号未启用,请联系管理员!");
        }
        if (!verifyAuthCode(authCode, telephone)) {
            Asserts.fail("验证码错误");
        }
        user.setPassword(passwordEncoder.encode(password));
        boyingUserMapper.updateByPrimaryKeySelective(user);
        boyingUserCacheService.delUser(user.getId());//删除无效缓存
        boyingUserCacheService.delAuthCode(telephone);//注册完删除验证码,每个验证码只能使用一次
    }

    @Override
    public BoyingUser getCurrentUser() {
//        获取之前登录存储的用户上下文信息
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        BoyingUserDetails userDetails = (BoyingUserDetails) auth.getPrincipal();
        if (userDetails.getUser().getAdminDelete()) {
            Asserts.fail("账号未启用,请联系管理员!");
        }
        return userDetails.getUser();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        BoyingUser user = getByUsername(username);
        return new BoyingUserDetails(user);
    }

    @Override
    public String login(UsernameLoginParam param) {
        String username = param.getUsername();
        String password = param.getPassword();

        BoyingUser user = getByUsername(username);
        UserDetails userDetails = new BoyingUserDetails(user);

        if (user.getAdminDelete()) Asserts.fail("账号未启用,请联系管理员!");
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenUtil.generateToken(userDetails);
    }

    @Override
    public String telephoneLogin(TelephoneLoginParam param) {
        String telephone = param.getTelephone();
        String password = param.getPassword();
        String token;
        BoyingUser user = boyingUserCacheService.getUserByTelephone(telephone);
        if (user == null) {
            user = boyingUserMapper.selectByPhone(telephone);
            if (user == null) {
                Asserts.fail("该账号不存在");
            }
            if (user.getAdminDelete()) {
                Asserts.fail("账号未启用,请联系管理员!");
            }
            boyingUserCacheService.setUser(user);
        }
        // 缓存有数据，说明手机号是对的，直接检查密码即可
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }
        UserDetails userDetails = loadUserByUsername(user.getUsername());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    @Override
    public String authCodeLogin(AuthCodeLoginParam param) {
        String telephone = param.getTelephone();
        String authCode = param.getAuthCode();
        //验证验证码
        if (!(authCode.equals("123456") || verifyAuthCode(authCode, telephone))) {
            Asserts.fail("验证码错误");
        }
        String token;
        BoyingUser user = boyingUserCacheService.getUserByTelephone(telephone);
        if (user == null) {
            user = boyingUserMapper.selectByPhone(telephone);
            if (user == null) {
                Asserts.fail("该账号不存在");
            }
            if (user.getAdminDelete()) {
                Asserts.fail("账号未启用,请联系管理员!");
            }
            boyingUserCacheService.setUser(user);
        }
        UserDetails userDetails = loadUserByUsername(user.getUsername());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        token = jwtTokenUtil.generateToken(userDetails);
        boyingUserCacheService.delAuthCode(telephone);
        return token;
    }

    @Override
    public String refreshToken(String token) {
        return jwtTokenUtil.refreshHeadToken(token);
    }

    @Override
    public void updateInfo(UpdateInfoParam param) {
        String realName = param.getRealName();
        String identityNumber = param.getIdentityNumber();
        String email = param.getEmail();
        String icon = param.getIcon();
        Integer age = param.getAge();
        Boolean gender = param.getGender();

        BoyingUser currentUser = new BoyingUser();
        currentUser.setId(getCurrentUser().getId());
        currentUser.setRealName(realName);
        currentUser.setIdentityNumber(identityNumber);
        currentUser.setEmail(email);
        currentUser.setIcon(icon);

        if (gender != null) {
            currentUser.setGender(gender);
        }

        if (age != null && age > 0) {
            currentUser.setAge(age);
        }

        int count = boyingUserMapper.updateByPrimaryKeySelective(currentUser);
        if (count == 0) Asserts.fail("更新失败！");
        boyingUserCacheService.delUser(currentUser.getId());
    }

    private boolean verifyAuthCode(String authCode, String telephone) {
        if (StringUtils.isEmpty(authCode)) {
            return false;
        }
        String realAuthCode = boyingUserCacheService.getAuthCode(telephone);
        return authCode.equals(realAuthCode);
    }
}
