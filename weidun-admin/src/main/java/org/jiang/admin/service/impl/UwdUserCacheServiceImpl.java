package org.jiang.admin.service.impl;

import org.jiang.admin.dao.UwdUserRoleRelationDao;
import org.jiang.admin.service.UwdUserCacheService;
import org.jiang.admin.service.UwdUserService;
import org.jiang.common.service.RedisService;
import org.jiang.mapper.UwdUserRoleRelationMapper;
import org.jiang.model.UwdResource;
import org.jiang.model.UwdUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description UwdUserCacheService实现类
 * @Author jiang
 * @Create 2021/1/6
 * @Version 1.0
 */
@Service
public class UwdUserCacheServiceImpl implements UwdUserCacheService {
    @Autowired
    private UwdUserService uwdUserService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UwdUserRoleRelationMapper uwdUserRoleRelationMapper;
    @Autowired
    private UwdUserRoleRelationDao uwdUserRoleRelationDao;
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.admin}")
    private String REDIS_KEY_ADMIN;
    @Value("${redis.key.resourceList}")
    private String REDIS_KEY_RESOURCE_LIST;

    @Override
    public void delUser(Long userId) {
        UwdUser user = uwdUserService.getItem(userId);
        if (user != null) {
            String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + user.getUsername();
            redisService.del(key);
        }
    }

    @Override
    public void delResourceList(Long userId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + userId;
        redisService.del(key);
    }

    @Override
    public void delResourceListByRole(Long roleId) {

    }

    @Override
    public void delResourceListByRoleIds(List<Long> roleIds) {

    }

    @Override
    public void delResourceListByResource(Long resourceId) {

    }

    @Override
    public UwdUser getUser(String username) {
        return null;
    }

    @Override
    public void setUser(UwdUser user) {

    }

    @Override
    public List<UwdResource> getResourceList(Long userId) {
        return null;
    }

    @Override
    public void setResourceList(Long userId, List<UwdResource> resourceList) {

    }
}
