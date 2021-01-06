package org.jiang.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.jiang.model.UwdUserRoleRelation;
import org.jiang.model.UwdUserRoleRelationExample;

public interface UwdUserRoleRelationMapper {
    long countByExample(UwdUserRoleRelationExample example);

    int deleteByExample(UwdUserRoleRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UwdUserRoleRelation record);

    int insertSelective(UwdUserRoleRelation record);

    List<UwdUserRoleRelation> selectByExample(UwdUserRoleRelationExample example);

    UwdUserRoleRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UwdUserRoleRelation record, @Param("example") UwdUserRoleRelationExample example);

    int updateByExample(@Param("record") UwdUserRoleRelation record, @Param("example") UwdUserRoleRelationExample example);

    int updateByPrimaryKeySelective(UwdUserRoleRelation record);

    int updateByPrimaryKey(UwdUserRoleRelation record);
}