package org.jiang.admin.service.impl;

import org.jiang.admin.service.UwdResourceService;
import org.jiang.admin.service.UwdUserCacheService;
import org.jiang.mapper.UwdResourceMapper;
import org.jiang.model.UwdResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description 后台资源管理Service实现类
 * @Author jiang
 * @Create 2021/1/6
 * @Version 1.0
 */
@Service
public class UwdResourceServiceImpl implements UwdResourceService {
    @Autowired
    private UwdResourceMapper uwdResourceMapper;
    @Autowired
    private UwdUserCacheService uwdUserCacheService;
    @Override
    public int create(UwdResource UwdResource) {
        UwdResource.setCreateTime(new Date());
        return uwdResourceMapper.insert(UwdResource);
    }

    @Override
    public int update(Long id, UwdResource UwdResource) {
        UwdResource.setId(id);
        int count = uwdResourceMapper.updateByPrimaryKeySelective(UwdResource);
        uwdUserCacheService.delResourceListByResource(id);
        return count;
    }

    @Override
    public UwdResource getItem(Long id) {
        return uwdResourceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(Long id) {
        int count = uwdResourceMapper.deleteByPrimaryKey(id);
        uwdUserCacheService.delResourceListByResource(id);
        return count;
    }

    @Override
    public List<UwdResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
        return null;
    }

    @Override
    public List<UwdResource> listAll() {
        return null;
    }
}
