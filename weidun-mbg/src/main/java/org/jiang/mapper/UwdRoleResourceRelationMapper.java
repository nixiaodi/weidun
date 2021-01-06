package org.jiang.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.jiang.model.UwdRoleResourceRelation;
import org.jiang.model.UwdRoleResourceRelationExample;

public interface UwdRoleResourceRelationMapper {
    long countByExample(UwdRoleResourceRelationExample example);

    int deleteByExample(UwdRoleResourceRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UwdRoleResourceRelation record);

    int insertSelective(UwdRoleResourceRelation record);

    List<UwdRoleResourceRelation> selectByExample(UwdRoleResourceRelationExample example);

    UwdRoleResourceRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UwdRoleResourceRelation record, @Param("example") UwdRoleResourceRelationExample example);

    int updateByExample(@Param("record") UwdRoleResourceRelation record, @Param("example") UwdRoleResourceRelationExample example);

    int updateByPrimaryKeySelective(UwdRoleResourceRelation record);

    int updateByPrimaryKey(UwdRoleResourceRelation record);
}