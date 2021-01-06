package org.jiang.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.jiang.model.UwdRole;
import org.jiang.model.UwdRoleExample;

public interface UwdRoleMapper {
    long countByExample(UwdRoleExample example);

    int deleteByExample(UwdRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UwdRole record);

    int insertSelective(UwdRole record);

    List<UwdRole> selectByExample(UwdRoleExample example);

    UwdRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UwdRole record, @Param("example") UwdRoleExample example);

    int updateByExample(@Param("record") UwdRole record, @Param("example") UwdRoleExample example);

    int updateByPrimaryKeySelective(UwdRole record);

    int updateByPrimaryKey(UwdRole record);
}