package org.jiang.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.jiang.model.UwdUserLoginLog;
import org.jiang.model.UwdUserLoginLogExample;

public interface UwdUserLoginLogMapper {
    long countByExample(UwdUserLoginLogExample example);

    int deleteByExample(UwdUserLoginLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UwdUserLoginLog record);

    int insertSelective(UwdUserLoginLog record);

    List<UwdUserLoginLog> selectByExample(UwdUserLoginLogExample example);

    UwdUserLoginLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UwdUserLoginLog record, @Param("example") UwdUserLoginLogExample example);

    int updateByExample(@Param("record") UwdUserLoginLog record, @Param("example") UwdUserLoginLogExample example);

    int updateByPrimaryKeySelective(UwdUserLoginLog record);

    int updateByPrimaryKey(UwdUserLoginLog record);
}