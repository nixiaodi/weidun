package org.jiang.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.jiang.admin.bo.AdminUserDetails;
import org.jiang.admin.dao.UwdUserRoleRelationDao;
import org.jiang.admin.dto.UpdateAdminPasswordParam;
import org.jiang.admin.dto.UwdUserParam;
import org.jiang.admin.service.UwdUserCacheService;
import org.jiang.admin.service.UwdUserService;
import org.jiang.common.exception.Asserts;
import org.jiang.common.util.RequestUtil;
import org.jiang.mapper.UwdUserLoginLogMapper;
import org.jiang.mapper.UwdUserMapper;
import org.jiang.mapper.UwdUserRoleRelationMapper;
import org.jiang.model.*;
import org.jiang.security.util.JwtTokenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
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
   private UwdUserMapper userMapper;
   @Autowired
   private UwdUserRoleRelationMapper userRoleRelationMapper;
   @Autowired
   private UwdUserRoleRelationDao userRoleRelationDao;
   @Autowired
   private UwdUserCacheService uwdUserCacheService;
   @Autowired
   private UwdUserLoginLogMapper uwdUserLoginLogMapper;


    @Override
    public UwdUser getUserByUsername(String username) {
        UwdUser user = uwdUserCacheService.getUser(username);
        if (user != null) return user;
        UwdUserExample example = new UwdUserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UwdUser> uwdUserList = userMapper.selectByExample(example);
        if (uwdUserList != null && uwdUserList.size() > 0) {
            user = uwdUserList.get(0);
            uwdUserCacheService.setUser(user);
            return user;
        }
        return null;
    }

    @Override
    public UwdUser register(UwdUserParam uwdUserParam) {
        UwdUser uwdUser = new UwdUser();
        BeanUtils.copyProperties(uwdUserParam,uwdUser);
        uwdUser.setCreateTime(new Date());
        uwdUser.setStatus(1);
        // 查询是否存在相同用户名的用户
        UwdUserExample example = new UwdUserExample();
        example.createCriteria().andUsernameEqualTo(uwdUser.getUsername());
        List<UwdUser> uwdUserList = userMapper.selectByExample(example);
        if (uwdUserList.size() > 0) {
            return null;
        }
        // 加密密码
        String encodePassword = passwordEncoder.encode(uwdUser.getPassword());
        uwdUser.setPassword(encodePassword);
        userMapper.insert(uwdUser);
        return uwdUser;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        // 密码经过加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if (!passwordEncoder.matches(password,userDetails.getPassword())) {
                Asserts.fail("密码不正确");
            }
            if (! userDetails.isEnabled()) {
                Asserts.fail("账号已被禁用");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            insertLoginLog(username);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}",e.getMessage());
        }
        return token;
    }

    /**
     * 添加登录日志
     * @param username 用户名
     */
    private void insertLoginLog(String username) {
        UwdUser uwdUser = getUserByUsername(username);
        if (uwdUser == null) return;
        UwdUserLoginLog loginLog = new UwdUserLoginLog();
        loginLog.setAdminId(uwdUser.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(RequestUtil.getRequestIp(request));
        uwdUserLoginLogMapper.insert(loginLog);
    }

    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }

    @Override
    public UwdUser getItem(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UwdUser> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        UwdUserExample example = new UwdUserExample();
        UwdUserExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andUsernameLike("%" + keyword + "%");
            example.or(example.createCriteria().andNickNameLike("%" + keyword + "%"));
        }
        return userMapper.selectByExample(example);
    }

    @Override
    public int update(Long id, UwdUser uwdUser) {
        uwdUser.setId(id);
        UwdUser rawUser = userMapper.selectByPrimaryKey(id);
        if (rawUser.getPassword().equals(uwdUser.getPassword())) {
            // 与原加密密码相同的不需要修改
            uwdUser.setPassword(null);
        } else {
            // 与原加密密码不同的需要加密修改
            if (StrUtil.isEmpty(uwdUser.getPassword())) {
                uwdUser.setPassword(null);
            } else {
                uwdUser.setPassword(passwordEncoder.encode(uwdUser.getPassword()));
            }
        }
        int count = userMapper.updateByPrimaryKeySelective(uwdUser);
        uwdUserCacheService.delUser(id);
        return count;
    }

    @Override
    public int delete(Long id) {
        uwdUserCacheService.delUser(id);
        int count = userMapper.deleteByPrimaryKey(id);
        uwdUserCacheService.delResourceListByResource(id);
        return count;
    }

    @Override
    public int updateRole(Long uwdUserId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        // 先删除原来的关系
        UwdUserRoleRelationExample userRoleRelationExample = new UwdUserRoleRelationExample();
        userRoleRelationExample.createCriteria().andUserIdEqualTo(uwdUserId);
        userRoleRelationMapper.deleteByExample(userRoleRelationExample);
        // 建立新关系
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<UwdUserRoleRelation> list = new ArrayList<>();
            for (Long roleId : roleIds) {
                UwdUserRoleRelation uwdUserRoleRelation = new UwdUserRoleRelation();
                uwdUserRoleRelation.setUserId(uwdUserId);
                uwdUserRoleRelation.setRoleId(roleId);
                list.add(uwdUserRoleRelation);
            }
            userRoleRelationDao.insertList(list);
        }
        uwdUserCacheService.delResourceList(uwdUserId);
        return count;
    }

    @Override
    public List<UwdRole> getRoleList(Long userId) {
        return userRoleRelationDao.getRoleList(userId);
    }

    @Override
    public List<UwdResource> getResourceList(Long userId) {
        List<UwdResource> resourceList = uwdUserCacheService.getResourceList(userId);
        if (CollUtil.isNotEmpty(resourceList)) {
            return resourceList;
        }
        resourceList = userRoleRelationDao.getResourceList(userId);
        if (CollUtil.isNotEmpty(resourceList)) {
            uwdUserCacheService.setResourceList(userId,resourceList);
        }
        return resourceList;
    }

    @Override
    public int updatePassword(UpdateAdminPasswordParam updatePasswordParam) {
        if (StrUtil.isEmpty(updatePasswordParam.getUsername())
            || StrUtil.isEmpty(updatePasswordParam.getOldPassword())
            || StrUtil.isEmpty(updatePasswordParam.getNewPassword())) {
            return -1;
        }
        UwdUserExample example = new UwdUserExample();
        example.createCriteria().andUsernameEqualTo(updatePasswordParam.getUsername());
        List<UwdUser> uwdUserList = userMapper.selectByExample(example);
        if (CollUtil.isEmpty(uwdUserList)) {
            return -2;
        }
        UwdUser uwdUser = uwdUserList.get(0);
        if (!passwordEncoder.matches(updatePasswordParam.getOldPassword(), uwdUser.getPassword())) {
            return -3;
        }
        uwdUser.setPassword(passwordEncoder.encode(updatePasswordParam.getNewPassword()));
        userMapper.updateByPrimaryKey(uwdUser);
        uwdUserCacheService.delUser(uwdUser.getId());
        return 1;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        // 获取用户信息
        UwdUser uwdUser = getUserByUsername(username);
        if (uwdUser != null) {
            List<UwdResource> resourceList = getResourceList(uwdUser.getId());
            return new AdminUserDetails(uwdUser,resourceList);
        }
        throw  new UsernameNotFoundException("用户名或密码错误");
    }
}
