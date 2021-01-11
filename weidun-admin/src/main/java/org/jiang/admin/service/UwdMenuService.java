package org.jiang.admin.service;

import org.jiang.admin.dto.UwdMenuNode;
import org.jiang.model.UwdMenu;

import java.util.List;

/**
 * @Description 后台管理菜单Service
 * @Author jiang
 * @Create 2021/1/7
 * @Version 1.0
 */
public interface UwdMenuService {
    /**
     * 创建后台菜单
     */
    int create(UwdMenu uwdMenu);
    /**
     * 修改后台菜单
     */
    int update(Long id,UwdMenu uwdMenu);
    /**
     * 根据ID获取菜单详情
     */
    UwdMenu getItem(Long id);
    /**
     * 根据ID删除菜单
     */
    int delete(Long id);
    /**
     * 分页查询后台菜单
     */
    List<UwdMenu> list(Long parentId,Integer pageSize,Integer pageNum);
    /**
     * 树形结构返回所有菜单列表
     */
    List<UwdMenuNode> treeList();
    /**
     * 修改菜单显示状态
     */
    int updateHidden(Long id,Integer hidden);
}
