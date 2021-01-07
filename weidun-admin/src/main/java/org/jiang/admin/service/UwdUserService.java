package org.jiang.admin.service;

import org.jiang.admin.dto.UpdateAdminPasswordParam;
import org.jiang.admin.dto.UwdUserParam;
import org.jiang.model.UwdResource;
import org.jiang.model.UwdRole;
import org.jiang.model.UwdUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 后台管理员service
 * @Author jiang
 * @Create 2021/1/6
 * @Version 1.0
 */
public interface UwdUserService {
    /**
     * 根据用户名获取用户信息
     */
    UwdUser getUserByUsername(String username);

    /**
     * 用户注册
     */
    UwdUser register(UwdUserParam uwdUserParam);

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username,String password);

    /**
     * 刷新token的功能
     * @param oldToken 旧的token
     */
    String refreshToken(String oldToken);

    /**
     * 根据用户id获取用户
     */
    UwdUser getItem(Long id);

    /**
     * 根据用户名或昵称分页查询用户
     */
    List<UwdUser> list(String keyword,Integer pageSize,Integer pageNum);

    /**
     * 修改指定用户信息
     */
    int update(Long id, UwdUser uwdUser);

    /**
     * 删除指定用户
     */
    int delete(Long id);

    /**
     * 修改用户角色信息
     */
    @Transactional
    int updateRole(Long uwdUserId,List<Long> roleIds);

    /**
     * 获取用户对于角色
     */
    List<UwdRole> getRoleList(Long userId);

    /**
     * 获取指定用户的可访问资源
     */
    List<UwdResource> getResourceList(Long userId);

    /**
     * 修改密码
     */
    int updatePassword(UpdateAdminPasswordParam updatePasswordParam);

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);
}
