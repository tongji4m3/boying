package com.tongji.boying.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.tongji.boying.common.service.RedisService;
import com.tongji.boying.dao.UmsAdminRoleDao;
import com.tongji.boying.mapper.AdminRoleMapper;
import com.tongji.boying.model.AdminUser;
import com.tongji.boying.model.AdminRole;
import com.tongji.boying.model.AdminRoleExample;
import com.tongji.boying.model.AdminResource;
import com.tongji.boying.service.UmsAdminCacheService;
import com.tongji.boying.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * UmsAdminCacheService实现类
 */
@Service
public class UmsAdminCacheServiceImpl implements UmsAdminCacheService
{
    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Autowired
    private UmsAdminRoleDao adminRoleDao;

    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Integer REDIS_EXPIRE;
    @Value("${redis.key.admin}")
    private String REDIS_KEY_ADMIN;
    @Value("${redis.key.resourceList}")
    private String REDIS_KEY_RESOURCE_LIST;

    @Override
    public void delAdmin(Integer adminId)
    {
        AdminUser AdminUser = adminService.getItem(adminId);
        if (AdminUser != null)
        {
            String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + AdminUser.getUsername();
            redisService.del(key);
        }
    }

    @Override
    public void delResourceList(Integer adminId)
    {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        redisService.del(key);
    }

    @Override
    public void delResourceListByRole(Integer roleId)
    {
        AdminRoleExample example = new AdminRoleExample();
        example.createCriteria().andIdEqualTo(roleId);
        List<AdminRole> relationList = adminRoleMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(relationList))
        {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = relationList.stream().map(relation -> keyPrefix + relation.getId()).collect(Collectors.toList());
            System.out.println("456");
            System.out.println(keys.toArray().toString());
            for (String s :keys){
                System.out.println(s);
            };
            redisService.del(keys);
        }
    }

    @Override
    public void delResourceListByRoleIds(List<Integer> roleIds)
    {
        AdminRoleExample example = new AdminRoleExample();
        example.createCriteria().andIdIn(roleIds);
        List<AdminRole> relationList = adminRoleMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(relationList))
        {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = relationList.stream().map(relation -> keyPrefix + relation.getId()).collect(Collectors.toList());
            redisService.del(keys);
        }
    }

    @Override
    public void delResourceListByResource(Integer resourceId)
    {
        List<Integer> adminIdList = adminRoleDao.getAdminIdList(resourceId);
        if (CollUtil.isNotEmpty(adminIdList))
        {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = adminIdList.stream().map(adminId -> keyPrefix + adminId).collect(Collectors.toList());
            redisService.del(keys);
        }
    }

    @Override
    public AdminUser getAdmin(String username)
    {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + username;
        return (AdminUser) redisService.get(key);
    }

    @Override
    public void setAdmin(AdminUser AdminUser)
    {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + AdminUser.getUsername();
        redisService.set(key, AdminUser, REDIS_EXPIRE);
    }

    @Override
    public List<AdminResource> getResourceList(Integer adminId)
    {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        return (List<AdminResource>) redisService.get(key);
    }

    @Override
    public void setResourceList(Integer adminId, List<AdminResource> resourceList)
    {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        redisService.set(key, resourceList, REDIS_EXPIRE);
    }
}
