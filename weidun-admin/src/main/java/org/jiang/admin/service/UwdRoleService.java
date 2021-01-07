package org.jiang.admin.service;

import org.jiang.model.UwdMenu;
import org.jiang.model.UwdResource;
import org.jiang.model.UwdRole;
import org.springframework.transaction.annotation.Transactional;
import sun.rmi.log.LogInputStream;

import java.util.List;

/**
 * @Description 后台角色管理Service
 * @Author jiang
 * @Create 2021/1/7
 * @Version 1.0
 */
public interface UwdRoleService {
    /**
     * 添加角色
     */
    int create(UwdRole role);

    /**
     * 修改角色信息
     */
    int update(Long id, UwdRole role);

    /**
     * 批量删除角色
     */
    int delete(List<Long> ids);

    /**
     * 获取所有角色列表
     */
    List<UwdRole> list();

    /**
     * 分页获取角色列表
     */
    List<UwdRole> list(String keyword, Integer pageSize, Integer pageNum);
    /**
     * 根据管理员ID获取对应菜单
     */
    List<UwdMenu> getMenuList(Long roleId);
    /**
     * 获取角色相关菜单
     */
    List<UwdMenu> listMenu(Long roleId);
    /**
     * 获取角色相关资源
     */
    List<UwdResource> listResource(Long roleId);
    /**
     * 给角色分配菜单
     */
    @Transactional
    int allocMenu(Long roleId,List<Long> menuIdS);
    /**
     * 给角色分配资源
     */
    @Transactional
    int allocResource(Long roleId,List<Long> resourceIds);
}
