package org.jiang.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.jiang.model.UwdResourceCategory;
import org.jiang.model.UwdResourceCategoryExample;

public interface UwdResourceCategoryMapper {
    long countByExample(UwdResourceCategoryExample example);

    int deleteByExample(UwdResourceCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UwdResourceCategory record);

    int insertSelective(UwdResourceCategory record);

    List<UwdResourceCategory> selectByExample(UwdResourceCategoryExample example);

    UwdResourceCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UwdResourceCategory record, @Param("example") UwdResourceCategoryExample example);

    int updateByExample(@Param("record") UwdResourceCategory record, @Param("example") UwdResourceCategoryExample example);

    int updateByPrimaryKeySelective(UwdResourceCategory record);

    int updateByPrimaryKey(UwdResourceCategory record);
}