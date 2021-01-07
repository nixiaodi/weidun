package org.jiang.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import org.jiang.admin.dao.UwdUserRoleRelationDao;
import org.jiang.admin.service.UwdUserCacheService;
import org.jiang.admin.service.UwdUserService;
import org.jiang.common.service.RedisService;
import org.jiang.mapper.UwdUserRoleRelationMapper;
import org.jiang.model.UwdResource;
import org.jiang.model.UwdUser;
import org.jiang.model.UwdUserRoleRelation;
import org.jiang.model.UwdUserRoleRelationExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        UwdUserRoleRelationExample example = new UwdUserRoleRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        List<UwdUserRoleRelation> userRoleRelationList = uwdUserRoleRelationMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(userRoleRelationList)) {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = userRoleRelationList.stream().map(relation -> keyPrefix + relation.getUserId()).collect(Collectors.toList());
            redisService.del(keys);
        }
    }

    @Override
    public void delResourceListByRoleIds(List<Long> roleIds) {
        UwdUserRoleRelationExample example = new UwdUserRoleRelationExample();
        example.createCriteria().andRoleIdIn(roleIds);
        List<UwdUserRoleRelation> relationList = uwdUserRoleRelationMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(relationList)) {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = relationList.stream().map(relation -> keyPrefix + relation.getUserId()).collect(Collectors.toList());
            redisService.del(keys);
        }
    }

    @Override
    public void delResourceListByResource(Long resourceId) {
        List<Long> userIdList = uwdUserRoleRelationDao.getUserIdList(resourceId);
        if (CollUtil.isNotEmpty(userIdList)) {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = userIdList.stream().map(userId -> keyPrefix + userId).collect(Collectors.toList());
            redisService.del(keys);
        }
    }

    @Override
    public UwdUser getUser(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + username;
        return ((UwdUser) redisService.get(key));
    }

    @Override
    public void setUser(UwdUser user) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + user.getUsername();
        redisService.set(key,user,REDIS_EXPIRE);
    }

    @Override
    public List<UwdResource> getResourceList(Long userId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + userId;
        return ((List<UwdResource>) redisService.get(key));
    }

    @Override
    public void setResourceList(Long userId, List<UwdResource> resourceList) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + userId;
        redisService.set(key,resourceList,REDIS_EXPIRE);
    }
}
