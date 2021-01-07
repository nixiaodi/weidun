package org.jiang.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.jiang.model.UwdRoleMenuRelation;
import org.jiang.model.UwdRoleMenuRelationExample;

public interface UwdRoleMenuRelationMapper {
    long countByExample(UwdRoleMenuRelationExample example);

    int deleteByExample(UwdRoleMenuRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UwdRoleMenuRelation record);

    int insertSelective(UwdRoleMenuRelation record);

    List<UwdRoleMenuRelation> selectByExample(UwdRoleMenuRelationExample example);

    UwdRoleMenuRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UwdRoleMenuRelation record, @Param("example") UwdRoleMenuRelationExample example);

    int updateByExample(@Param("record") UwdRoleMenuRelation record, @Param("example") UwdRoleMenuRelationExample example);

    int updateByPrimaryKeySelective(UwdRoleMenuRelation record);

    int updateByPrimaryKey(UwdRoleMenuRelation record);
}