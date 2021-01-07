package org.jiang.admin.service.impl;

import com.github.pagehelper.PageHelper;
import org.jiang.admin.dto.UwdMenuNode;
import org.jiang.admin.service.UwdMenuService;
import org.jiang.mapper.UwdMenuMapper;
import org.jiang.model.UwdMenu;
import org.jiang.model.UwdMenuExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author jiang
 * @Create 2021/1/7
 * @Version 1.0
 */
@Service
public class UwdMenuServiceImpl implements UwdMenuService {
    @Autowired
    private UwdMenuMapper uwdMenuMapper;


    @Override
    public int create(UwdMenu uwdMenu) {
        uwdMenu.setCreateTime(new Date());
        updateLevel(uwdMenu);
        return uwdMenuMapper.insert(uwdMenu);
    }

    @Override
    public int update(Long id, UwdMenu uwdMenu) {
        uwdMenu.setId(id);
        updateLevel(uwdMenu);
        return uwdMenuMapper.updateByPrimaryKeySelective(uwdMenu);
    }

    /**
     * 修改菜单层级
     */
    private void updateLevel(UwdMenu uwdMenu) {
        if (uwdMenu.getParentId() == 0) {
            // 没有父菜单设置为一级菜单
            uwdMenu.setLevel(0);
        } else {
            // 有父菜单时选择根据父菜单level设置
            UwdMenu parentMenu = uwdMenuMapper.selectByPrimaryKey(uwdMenu.getParentId());
            if (parentMenu != null) {
                uwdMenu.setLevel(parentMenu.getLevel() + 1);
            } else {
                uwdMenu.setLevel(0);
            }
        }
    }

    @Override
    public UwdMenu getItem(Long id) {
        return uwdMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(Long id) {
        return uwdMenuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<UwdMenu> lsit(Long parentId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        UwdMenuExample example = new UwdMenuExample();
        example.setOrderByClause("sort desc");
        example.createCriteria().andParentIdEqualTo(parentId);
        return uwdMenuMapper.selectByExample(example);
    }

    @Override
    public List<UwdMenuNode> treeList() {
        List<UwdMenu> uwdMenuList = uwdMenuMapper.selectByExample(new UwdMenuExample());
        List<UwdMenuNode> result = uwdMenuList.stream()
                .filter(menu -> menu.getParentId().equals(0L))
                .map(menu -> covertMenuNode(menu, uwdMenuList)).collect(Collectors.toList());
        return result;
    }

    @Override
    public int updateHidden(Long id, Integer hidden) {
        UwdMenu umsMenu = new UwdMenu();
        umsMenu.setId(id);
        umsMenu.setHidden(hidden);
        return uwdMenuMapper.updateByPrimaryKeySelective(umsMenu);
    }

    /**
     * 将UwdMenu转化为UwdMenuNode并设置children属性
     */
    private UwdMenuNode covertMenuNode(UwdMenu uwdMenu,List<UwdMenu> menuList) {
        UwdMenuNode node = new UwdMenuNode();
        BeanUtils.copyProperties(uwdMenu,node);
        List<UwdMenuNode> children = menuList.stream()
                .filter(subMenu -> subMenu.getParentId().equals(uwdMenu.getId()))
                .map(subMenu -> covertMenuNode(subMenu, menuList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
