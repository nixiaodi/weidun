package org.jiang.admin.service.impl;

import com.github.pagehelper.PageHelper;
import org.checkerframework.checker.units.qual.A;
import org.jiang.admin.dao.UwdRoleDao;
import org.jiang.admin.service.UwdRoleService;
import org.jiang.admin.service.UwdUserCacheService;
import org.jiang.mapper.UwdRoleMapper;
import org.jiang.mapper.UwdRoleMenuRelationMapper;
import org.jiang.mapper.UwdRoleResourceRelationMapper;
import org.jiang.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @Description 后台角色管理Service实现类
 * @Author jiang
 * @Create 2021/1/7
 * @Version 1.0
 */
@Service
public class UwdRoleServiceImpl implements UwdRoleService {
    @Autowired
    private UwdRoleMapper uwdRoleMapper;
    @Autowired
    private UwdRoleMenuRelationMapper uwdRoleMenuRelationMapper;
    @Autowired
    private UwdRoleResourceRelationMapper uwdRoleResourceRelationMapper;
    @Autowired
    private UwdRoleDao uwdRoleDao;
    @Autowired
    private UwdUserCacheService uwdUserCacheService;

    @Override
    public int create(UwdRole role) {
        role.setCreateTime(new Date());
        role.setUserCount(0);
        role.setSort(0);
        return uwdRoleMapper.insert(role);
    }

    @Override
    public int update(Long id, UwdRole role) {
        role.setId(id);
        return uwdRoleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public int delete(List<Long> ids) {
        UwdRoleExample example = new UwdRoleExample();
        example.createCriteria().andIdIn(ids);
        int count = uwdRoleMapper.deleteByExample(example);
        uwdUserCacheService.delResourceListByRoleIds(ids);
        return count;
    }

    @Override
    public List<UwdRole> list() {
        return uwdRoleMapper.selectByExample(new UwdRoleExample());
    }

    @Override
    public List<UwdRole> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UwdRoleExample example = new UwdRoleExample();
        if (!StringUtils.isEmpty(keyword)) {
            example.createCriteria().andNameLike("%" + keyword + "%");
        }
        return uwdRoleMapper.selectByExample(example);
    }

    @Override
    public List<UwdMenu> getMenuList(Long roleId) {
        return uwdRoleDao.getMenuList(roleId);
    }

    @Override
    public List<UwdMenu> listMenu(Long roleId) {
        return uwdRoleDao.getMenuListByRoleId(roleId);
    }

    @Override
    public List<UwdResource> listResource(Long roleId) {
        return uwdRoleDao.getResourceListByRoleId(roleId);
    }

    @Override
    public int allocMenu(Long roleId, List<Long> menuIdS) {
        // 先删除原有关系
        UwdRoleMenuRelationExample example = new UwdRoleMenuRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        uwdRoleMenuRelationMapper.deleteByExample(example);
        // 批量插入新的关系
        for (Long menuId : menuIdS) {
            UwdRoleMenuRelation relation = new UwdRoleMenuRelation();
            relation.setRoleId(roleId);
            relation.setMenuId(menuId);
            uwdRoleMenuRelationMapper.insert(relation);
        }
        return menuIdS.size();
    }

    @Override
    public int allocResource(Long roleId, List<Long> resourceIds) {
        // 先删除原有关系
        UwdRoleMenuRelationExample example = new UwdRoleMenuRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        uwdRoleMenuRelationMapper.deleteByExample(example);
        // 批量插入新的关系
        for (Long resourceId : resourceIds) {
            UwdRoleResourceRelation relation = new UwdRoleResourceRelation();
            relation.setResourceId(resourceId);
            relation.setRoleId(roleId);
            uwdRoleResourceRelationMapper.insert(relation);
        }
        return resourceIds.size();
    }
}
