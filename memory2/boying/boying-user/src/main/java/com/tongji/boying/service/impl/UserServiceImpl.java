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
import com.tongji.boying.dto.userParam.*;
import com.tongji.boying.mapper.BoyingUserMapper;
import com.tongji.boying.model.BoyingUser;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
    private BoyingUserMapper userMapper;

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
    public BoyingUser getByUsername(String username) {
        return null;
    }

    /*@Override
    public BoyingUser getByUsername(String username) {
        BoyingUser user = userCacheService.getUser(username);
        if (user != null) return user; //缓存里面有数据
        BoyingUserExample example = new BoyingUserExample();
        example.createCriteria().andUsernameEqualTo(username);//根据userExample进行where语句的查询
        List<BoyingUser> userList = userMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(userList)) {
            Asserts.fail("用户名或密码错误");

        }
        user = userList.get(0);
        //账号未启用
        if (user.getAdminDelete() == 1) {
            Asserts.fail("账号未启用,请联系管理员!");
        }
        userCacheService.setUser(user);//将查询到的数据放入缓存中
        return user;
    }
*/

    @Override
    @DateTimeFormat
    public void register(UserRegisterParam param) {

    }

    /*@Override
    @DateTimeFormat
    public void register(UserRegisterParam param) {
        String authCode = param.getAuthCode();
        String telephone = param.getTelephone();
        String username = param.getUsername();
        String password = param.getPassword();
        String icon = param.getIcon();

        //验证验证码
        if (!verifyAuthCode(authCode, telephone)) {
            Asserts.fail("验证码错误");
        }
        //查询是否已有该用户
        BoyingUserExample example = new BoyingUserExample();
        //用户名,手机号唯一
        example.createCriteria().andUsernameEqualTo(username);
        example.or(example.createCriteria().andPhoneEqualTo(telephone));
        List<BoyingUser> users = userMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(users)) {
            Asserts.fail("该用户已经存在或手机号已注册");
        }
        //没有该用户进行添加操作
        BoyingUser user = new BoyingUser();
        user.setUsername(username);
        user.setPhone(telephone);
        user.setPassword(passwordEncoder.encode(password));//存储加密后的
        //设置默认头像
        if (StringUtils.isEmpty(icon)) {
            icon = "https://tongji4m3.oss-cn-beijing.aliyuncs.com/f_f_object_156_s512_f_object_156_0.png";
        }
        user.setIcon(icon);
        userMapper.insertSelective(user);
        //注册完删除验证码,每个验证码只能使用一次
        userCacheService.delAuthCode(telephone);
    }*/

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
    public void updatePassword(UpdatePasswordParam param) {

    }

   /* @Override
    public void updatePassword(UpdatePasswordParam param) {
        String telephone = param.getTelephone();
        String password = param.getPassword();
        String authCode = param.getAuthCode();

        BoyingUserExample example = new BoyingUserExample();
        example.createCriteria().andPhoneEqualTo(telephone);
        List<BoyingUser> userList = userMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(userList)) {
            Asserts.fail("该账号不存在");
        }
        //验证验证码
        if (!verifyAuthCode(authCode, telephone)) {
            Asserts.fail("验证码错误");
        }
        BoyingUser user = userList.get(0);
        user.setPassword(passwordEncoder.encode(password));//密码加密
        userMapper.updateByPrimaryKeySelective(user);//只更新不为空的字段

        userCacheService.delUser(user.getId());//删除无效缓存

        //注册完删除验证码,每个验证码只能使用一次
        userCacheService.delAuthCode(telephone);
    }*/

    @Override
    public BoyingUser getCurrentUser() {
//        获取之前登录存储的用户上下文信息
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        BoyingUserDetails userDetails = (BoyingUserDetails) auth.getPrincipal();
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

        String token = null;
        //密码需要客户端加密后传递,但是传递的仍然是明文
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
//            获取该用户的上下文信息
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
    public String telephoneLogin(TelephoneLoginParam param) {
        return null;
    }

   /* @Override
    public String telephoneLogin(TelephoneLoginParam param) {
        String telephone = param.getTelephone();
        String password = param.getPassword();
        String token = null;
        //密码需要客户端加密后传递,但是传递的仍然是明文
        try {
            BoyingUser user = userCacheService.getUserByTelephone(telephone);
            //缓存里面没有数据
            if (user == null) {
                BoyingUserExample example = new BoyingUserExample();
                example.createCriteria().andPhoneEqualTo(telephone);//根据userExample进行where语句的查询
                List<BoyingUser> userList = userMapper.selectByExample(example);
                if (CollectionUtils.isEmpty(userList)) {
                    Asserts.fail("手机号不存在");
                }
                user = userList.get(0);
                //账号未启用
                if (user.getAdminDelete() == 1) {
                    Asserts.fail("账号未启用,请联系管理员!");
                }
                userCacheService.setUser(user);//将查询到的数据放入缓存中
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
*/

    @Override
    public String authCodeLogin(AuthCodeLoginParam param) {
        return null;
    }


    /*@Override
    public String authCodeLogin(AuthCodeLoginParam param) {
        String telephone = param.getTelephone();
        String authCode = param.getAuthCode();
        //验证验证码
        if (!verifyAuthCode(authCode, telephone)) {
            Asserts.fail("验证码错误");
        }
        String token = null;
        //密码需要客户端加密后传递,但是传递的仍然是明文
        try {
            BoyingUser user = userCacheService.getUserByTelephone(telephone);
            //缓存里面没有数据
            if (user == null) {
                BoyingUserExample example = new BoyingUserExample();
                example.createCriteria().andPhoneEqualTo(telephone);//根据userExample进行where语句的查询
                List<BoyingUser> userList = userMapper.selectByExample(example);
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
*/
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
        if (gender) {
            currentUser.setGender(1);
        }
        else {
            currentUser.setGender(0);
        }

        if (age > 0) {
            currentUser.setAge(age);
        }
        int count = userMapper.updateByPrimaryKeySelective(currentUser);//只更新不为空的字段
        if (count == 0) Asserts.fail("更新失败！");
        userCacheService.delUser(currentUser.getId());//删除无效缓存
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
