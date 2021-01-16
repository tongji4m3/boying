package com.tongji.boying.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.tongji.boying.common.exception.Asserts;
import com.tongji.boying.config.AdminUserDetails;
import com.tongji.boying.dto.adminParam.UsernameLoginParam;
import com.tongji.boying.mapper.BoyingAdminMapper;
import com.tongji.boying.model.BoyingAdmin;
import com.tongji.boying.model.BoyingAdminExample;
import com.tongji.boying.security.util.JwtTokenUtil;
import com.tongji.boying.service.UmsAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * UmsAdminService实现类
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private BoyingAdminMapper adminMapper;


    public BoyingAdmin getAdminByUsername(String username) {
        BoyingAdminExample example = new BoyingAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<BoyingAdmin> adminList = adminMapper.selectByExample(example);
        if (CollUtil.isEmpty(adminList)) Asserts.fail("管理员账号不存在！");
        return adminList.get(0);
    }

    @Override
    public BoyingAdmin getCurrentAdmin() {
        //        获取之前登录存储的用户上下文信息
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        AdminUserDetails userDetails = (AdminUserDetails) auth.getPrincipal();
        return userDetails.getAdmin();
    }


    @Override
    public String login(UsernameLoginParam param) {
        String username = param.getUsername();
        String password = param.getPassword();
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);

            if (!passwordEncoder.matches(password, userDetails.getPassword())) Asserts.fail("密码不正确");

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);

            //修改登录时间
            BoyingAdmin currentAdmin = getCurrentAdmin();
            currentAdmin.setLastTime(new Date());
            adminMapper.updateByPrimaryKey(currentAdmin);
        }
        catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        BoyingAdmin admin = getAdminByUsername(username);
        return new AdminUserDetails(admin);
    }
}
