package org.jiang.admin.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.jiang.model.UwdResource;
import org.jiang.model.UwdRole;
import org.jiang.model.UwdUserRoleRelation;

import java.util.List;

/**
 * @Description 自定义用户和角色管理Dao
 * @Author jiang
 * @Create 2021/1/6
 * @Version 1.0
 */
public interface UwdUserRoleRelationDao {
    /**
     * 批量插入用户角色关系
     */
    int insertList(@Param("list") List<UwdUserRoleRelation> uwdUserRoleRelationList);
    /**
     * 获取用户角色
     */
    List<UwdRole> getRoleList(@Param("userId") Long userId);
    /**
     * 获取用户所有可访问资源
     */
    List<UwdResource> getResourceList(@Param("userId") Long userId);
    /**
     * 获取资源相关用户ID列表
     */
    List<Long> getUserIdList(@Param("resourceId") Long resourceId);
}
