package org.jiang.admin.service;

import org.jiang.model.UwdResourceCategory;

import java.util.List;

/**
 * @Description 后台资源分类管理Service
 * @Author jiang
 * @Create 2021/1/7
 * @Version 1.0
 */
public interface UwdResourceCategoryService {
    /**
     * 获取所有资源分类
     */
    List<UwdResourceCategory> listAll();

    /**
     * 创建资源分类
     */
    int create(UwdResourceCategory uwdResourceCategory);

    /**
     * 修改资源分类
     */
    int update(Long id, UwdResourceCategory uwdResourceCategory);

    /**
     * 删除资源分类
     */
    int delete(Long id);
}
