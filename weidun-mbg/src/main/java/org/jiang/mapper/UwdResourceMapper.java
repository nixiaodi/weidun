package org.jiang.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.jiang.model.UwdResource;
import org.jiang.model.UwdResourceExample;

public interface UwdResourceMapper {
    long countByExample(UwdResourceExample example);

    int deleteByExample(UwdResourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UwdResource record);

    int insertSelective(UwdResource record);

    List<UwdResource> selectByExample(UwdResourceExample example);

    UwdResource selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UwdResource record, @Param("example") UwdResourceExample example);

    int updateByExample(@Param("record") UwdResource record, @Param("example") UwdResourceExample example);

    int updateByPrimaryKeySelective(UwdResource record);

    int updateByPrimaryKey(UwdResource record);
}