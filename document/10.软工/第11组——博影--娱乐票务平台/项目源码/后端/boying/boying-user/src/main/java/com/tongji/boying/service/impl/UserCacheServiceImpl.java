package com.tongji.boying.service.impl;

import com.tongji.boying.common.service.RedisService;
import com.tongji.boying.mapper.BoyingUserMapper;
import com.tongji.boying.model.BoyingUser;
import com.tongji.boying.service.UserCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserCacheServiceImpl implements UserCacheService {
    @Autowired
    private RedisService redisService;
    @Autowired
    private BoyingUserMapper userMapper;
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    /**    除验证码之外的过期时间
     *
     */
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.expire.authCode}")
    private Long REDIS_EXPIRE_AUTH_CODE;
    @Value("${redis.key.user}")
    private String REDIS_KEY_USER;
    @Value("${redis.key.authCode}")
    private String REDIS_KEY_AUTH_CODE;

    @Override
    public void delUser(int userId) {
        BoyingUser user = userMapper.selectByPrimaryKey(userId);
        if (user != null) {
            String key = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + user.getUsername();
            String key2 = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + user.getPhone();
            redisService.del(key);
            redisService.del(key2);
        }
    }

    @Override
    public BoyingUser getUser(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + username;

        return (BoyingUser) redisService.get(key);
    }

    @Override
    public BoyingUser getUserByTelephone(String telephone) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + telephone;

        return (BoyingUser) redisService.get(key);
    }

    @Override
    public void setUser(BoyingUser user) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + user.getUsername();
        String key2 = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + user.getPhone();
        redisService.set(key, user, REDIS_EXPIRE);
        redisService.set(key2, user, REDIS_EXPIRE);
    }

    @Override
    public void setAuthCode(String telephone, String authCode) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_AUTH_CODE + ":" + telephone;
        redisService.set(key, authCode, REDIS_EXPIRE_AUTH_CODE);
    }

    @Override
    public String getAuthCode(String telephone) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_AUTH_CODE + ":" + telephone;
        return (String) redisService.get(key);
    }

    @Override
    public void delAuthCode(String telephone) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_AUTH_CODE + ":" + telephone;
        redisService.del(key);
    }
}
