package org.jiang.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.jiang.model.UwdMenu;
import org.jiang.model.UwdMenuExample;

public interface UwdMenuMapper {
    long countByExample(UwdMenuExample example);

    int deleteByExample(UwdMenuExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UwdMenu record);

    int insertSelective(UwdMenu record);

    List<UwdMenu> selectByExample(UwdMenuExample example);

    UwdMenu selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UwdMenu record, @Param("example") UwdMenuExample example);

    int updateByExample(@Param("record") UwdMenu record, @Param("example") UwdMenuExample example);

    int updateByPrimaryKeySelective(UwdMenu record);

    int updateByPrimaryKey(UwdMenu record);
}