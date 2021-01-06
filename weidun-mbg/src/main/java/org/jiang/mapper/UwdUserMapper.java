package org.jiang.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.jiang.model.UwdUser;
import org.jiang.model.UwdUserExample;

public interface UwdUserMapper {
    long countByExample(UwdUserExample example);

    int deleteByExample(UwdUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UwdUser record);

    int insertSelective(UwdUser record);

    List<UwdUser> selectByExample(UwdUserExample example);

    UwdUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UwdUser record, @Param("example") UwdUserExample example);

    int updateByExample(@Param("record") UwdUser record, @Param("example") UwdUserExample example);

    int updateByPrimaryKeySelective(UwdUser record);

    int updateByPrimaryKey(UwdUser record);
}