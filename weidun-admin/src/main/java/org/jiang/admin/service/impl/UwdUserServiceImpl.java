package org.jiang.admin.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.jiang.admin.dao.UwdUserRoleRelationDao;
import org.jiang.admin.dto.UpdateAdminPasswordParam;
import org.jiang.admin.service.UwdUserCacheService;
import org.jiang.admin.service.UwdUserService;
import org.jiang.mapper.UwdUserMapper;
import org.jiang.mapper.UwdUserRoleRelationMapper;
import org.jiang.model.UwdResource;
import org.jiang.model.UwdRole;
import org.jiang.model.UwdUser;
import org.jiang.security.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description UwdUserService实现类
 * @Author jiang
 * @Create 2021/1/6
 * @Version 1.0
 */
@Service
@Slf4j
public class UwdUserServiceImpl implements UwdUserService {
   @Autowired
   private JwtTokenUtil jwtTokenUtil;
   @Autowired
   private PasswordEncoder passwordEncoder;
   @Autowired
   private UwdUserMapper uwdUserMapper;
   @Autowired
   private UwdUserRoleRelationMapper uwdUserRoleRelationMapper;
   @Autowired
   private UwdUserRoleRelationDao uwdUserRoleRelationDao;
   @Autowired
   private UwdUserCacheService uwdUserCacheService;


    @Override
    public UwdUser getUserByUsername(String username) {
        return null;
    }

    @Override
    public UwdUser register(UwdUser UwdUser) {
        return null;
    }

    @Override
    public String login(String username, String password) {
        return null;
    }

    @Override
    public String refreshToken(String oldToken) {
        return null;
    }

    @Override
    public UwdUser getItem(Long id) {
        return null;
    }

    @Override
    public List<UwdUser> list(String keyword, Integer pageSize, Integer pageNum) {
        return null;
    }

    @Override
    public int update(Long id, UwdUser uwdUser) {
        return 0;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public int updateRole(Long uwdUserId, List<Long> roleIds) {
        return 0;
    }

    @Override
    public List<UwdRole> getRoleList(Long userId) {
        return null;
    }

    @Override
    public List<UwdResource> getResourceList(Long userId) {
        return null;
    }

    @Override
    public int updatePassword(UpdateAdminPasswordParam updatePasswordParam) {
        return 0;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return null;
    }
}
