package org.jiang.admin.service;

import org.jiang.model.UwdResource;

import java.util.List;

/**
 * @Description 后台资源管理Service
 * @Author jiang
 * @Create 2021/1/6
 * @Version 1.0
 */
public interface UwdResourceService {
    /**
     * 添加资源
     */
    int create(UwdResource UwdResource);

    /**
     * 修改资源
     */
    int update(Long id, UwdResource UwdResource);

    /**
     * 获取资源详情
     */
    UwdResource getItem(Long id);

    /**
     * 删除资源
     */
    int delete(Long id);

    /**
     * 分页查询资源
     */
    List<UwdResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum);

    /**
     * 查询全部资源
     */
    List<UwdResource> listAll();
}
