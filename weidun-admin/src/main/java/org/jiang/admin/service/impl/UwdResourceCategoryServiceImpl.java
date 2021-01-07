package org.jiang.admin.service.impl;

import org.jiang.admin.service.UwdResourceCategoryService;
import org.jiang.mapper.UwdResourceCategoryMapper;
import org.jiang.model.UwdResourceCategory;
import org.jiang.model.UwdResourceCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description 后台资源分类管理Service实现类
 * @Author jiang
 * @Create 2021/1/7
 * @Version 1.0
 */
@Service
public class UwdResourceCategoryServiceImpl implements UwdResourceCategoryService {
    @Autowired
    private UwdResourceCategoryMapper uwdResourceCategoryMapper;

    @Override
    public List<UwdResourceCategory> listAll() {
        UwdResourceCategoryExample example = new UwdResourceCategoryExample();
        example.setOrderByClause("sort desc");
        return uwdResourceCategoryMapper.selectByExample(example);
    }

    @Override
    public int create(UwdResourceCategory uwdResourceCategory) {
        uwdResourceCategory.setCreateTime(new Date());
        return uwdResourceCategoryMapper.insert(uwdResourceCategory);
    }

    @Override
    public int update(Long id, UwdResourceCategory uwdResourceCategory) {
        uwdResourceCategory.setId(id);
        return uwdResourceCategoryMapper.updateByPrimaryKeySelective(uwdResourceCategory);
    }

    @Override
    public int delete(Long id) {
        return uwdResourceCategoryMapper.deleteByPrimaryKey(id);
    }
}
