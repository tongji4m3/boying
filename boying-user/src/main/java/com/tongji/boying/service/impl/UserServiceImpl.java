package com.tongji.boying.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.config.BoyingUserDetails;
import com.tongji.boying.mapper.UserMapper;
import com.tongji.boying.model.User;
import com.tongji.boying.model.UserExample;
import com.tongji.boying.security.util.JwtTokenUtil;
import com.tongji.boying.service.UserCacheService;
import com.tongji.boying.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 * 用户管理Service实现类
 */
@Service
public class UserServiceImpl implements UserService {
    //    便于日志的打印
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    /**
     * 用于密码加密
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * token工具类
     */
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 对用户信息进行一些缓存操作
     */
    @Autowired
    private UserCacheService userCacheService;
    /**
     * 验证码的前缀与过期时间
     */
    @Value("${redis.key.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Override
    public User getByUsername(String username) {
        User user = userCacheService.getUser(username);
        if (user != null) return user; //缓存里面有数据
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);//根据userExample进行where语句的查询
        List<User> userList = userMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(userList)) {
            user = userList.get(0);
            userCacheService.setUser(user);//将查询到的数据放入缓存中
            return user;
        }
        return null;
    }


    @Override
    @DateTimeFormat
    public void register(String username, String password, String telephone, String authCode, String icon) {
        //验证验证码
        if (!verifyAuthCode(authCode, telephone)) {
            Asserts.fail("验证码错误");
        }
        //查询是否已有该用户
        UserExample example = new UserExample();
        //用户名,手机号唯一
        example.createCriteria().andUsernameEqualTo(username);
        example.or(example.createCriteria().andPhoneEqualTo(telephone));
        List<User> users = userMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(users)) {
            Asserts.fail("该用户已经存在或手机号已注册");
        }
        //没有该用户进行添加操作
        User user = new User();
        user.setUsername(username);
        user.setPhone(telephone);
        user.setCreateTime(new Date());
        user.setPassword(passwordEncoder.encode(password));//存储加密后的
        user.setUserstatus(true);
        user.setIcon(icon);
        userMapper.insert(user);
        //注册完删除验证码,每个验证码只能使用一次
        userCacheService.delAuthCode(telephone);
    }

    @Override
    public void generateAuthCode(String telephone) {
        //简单生成6位验证码
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        //为该手机号生成验证码
        userCacheService.setAuthCode(telephone, sb.toString());

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
        }
        catch (ServerException e) {
            e.printStackTrace();
        }
        catch (ClientException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void updatePassword(String telephone, String password, String authCode) {
        UserExample example = new UserExample();
        example.createCriteria().andPhoneEqualTo(telephone);
        List<User> userList = userMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(userList)) {
            Asserts.fail("该账号不存在");
        }
        //验证验证码
        if (!verifyAuthCode(authCode, telephone)) {
            Asserts.fail("验证码错误");
        }
        User user = userList.get(0);
        user.setPassword(passwordEncoder.encode(password));//密码加密
        userMapper.updateByPrimaryKeySelective(user);//只更新不为空的字段

        userCacheService.delUser(user.getUserId());//删除无效缓存

        //注册完删除验证码,每个验证码只能使用一次
        userCacheService.delAuthCode(telephone);
    }

    @Override
    public User getCurrentUser() {
//        获取之前登录存储的用户上下文信息
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        BoyingUserDetails userDetails = (BoyingUserDetails) auth.getPrincipal();
        return userDetails.getUser();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = getByUsername(username);
        if (user != null) {
            return new BoyingUserDetails(user);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递,但是传递的仍然是明文
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
//            获取该用户的上下文信息（如他的角色列表）
//            username和password被获得后封装到一个UsernamePasswordAuthenticationToken
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//            围绕该用户建立安全上下文（security context）
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        }
        catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }


    @Override
    public String telephoneLogin(String telephone, String password) {
        String token = null;
        //密码需要客户端加密后传递,但是传递的仍然是明文
        try {
            User user = userCacheService.getUserByTelephone(telephone);
            //缓存里面没有数据
            if (user == null) {
                UserExample example = new UserExample();
                example.createCriteria().andPhoneEqualTo(telephone);//根据userExample进行where语句的查询
                List<User> userList = userMapper.selectByExample(example);
                if (!CollectionUtils.isEmpty(userList)) {
                    user = userList.get(0);
                    userCacheService.setUser(user);//将查询到的数据放入缓存中
                }
                else {
                    Asserts.fail("手机号不存在");
                }
                if (!passwordEncoder.matches(password, user.getPassword())) {
                    throw new BadCredentialsException("密码不正确");
                }
            }
            UserDetails userDetails = loadUserByUsername(user.getUsername());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        }
        catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public String authCodeLogin(String telephone, String authCode) {
        //验证验证码
        if (!verifyAuthCode(authCode, telephone)) {
            Asserts.fail("验证码错误");
        }
        String token = null;
        //密码需要客户端加密后传递,但是传递的仍然是明文
        try {
            User user = userCacheService.getUserByTelephone(telephone);
            //缓存里面没有数据
            if (user == null) {
                UserExample example = new UserExample();
                example.createCriteria().andPhoneEqualTo(telephone);//根据userExample进行where语句的查询
                List<User> userList = userMapper.selectByExample(example);
                if (!CollectionUtils.isEmpty(userList)) {
                    user = userList.get(0);
                    userCacheService.setUser(user);//将查询到的数据放入缓存中
                }
                //不需要在校验手机号是否存在
            }
            UserDetails userDetails = loadUserByUsername(user.getUsername());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        }
        catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        //注册完删除验证码,每个验证码只能使用一次
        userCacheService.delAuthCode(telephone);
        return token;
    }

    @Override
    public void setDefaultFrequent(Integer frequentId) {
        User currentUser = getCurrentUser();
        currentUser.setDefaultFrequent(frequentId);
    }

    @Override
    public void setDefaultAddress(Integer addressId) {
        User currentUser = getCurrentUser();
        currentUser.setDefaultAddress(addressId);
    }

    @Override
    public String refreshToken(String token) {
        return jwtTokenUtil.refreshHeadToken(token);
    }

    @Override
    public void updateInfo(String realName, String identityNumber, String email, String icon, int age, boolean gender) {
        User currentUser = getCurrentUser();
        currentUser.setRealName(realName);
        currentUser.setIdentityNumber(identityNumber);
        currentUser.setEmail(email);
        currentUser.setIcon(icon);
        currentUser.setGender(gender);
        if (age > 0) {
            currentUser.setAge(age);
        }
        userMapper.updateByPrimaryKeySelective(currentUser);//只更新不为空的字段
        userCacheService.delUser(currentUser.getUserId());//删除无效缓存
    }

    //对输入的验证码进行校验
    private boolean verifyAuthCode(String authCode, String telephone) {
        if (StringUtils.isEmpty(authCode)) {
            return false;
        }
//        redis中存储了该手机号未过期的验证码
        String realAuthCode = userCacheService.getAuthCode(telephone);
        return authCode.equals(realAuthCode);
    }
}
