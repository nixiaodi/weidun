package org.jiang.admin.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.jiang.model.UwdMenu;
import org.jiang.model.UwdResource;

import java.util.List;

/**
 * @Description 自定义后台角色Dao
 * @Author jiang
 * @Create 2021/1/7
 * @Version 1.0
 */
public interface UwdRoleDao {
    /**
     * 根据后台用户ID获取菜单
     */
    List<UwdMenu> getMenuList(@Param("userId") Long userId);
    /**
     * 根据角色ID获取菜单
     */
    List<UwdMenu> getMenuListByRoleId(@Param("roleId") Long roleId);
    /**
     * 根据角色ID获取资源
     */
    List<UwdResource> getResourceListByRoleId(@Param("roleId") Long roleId);
}
