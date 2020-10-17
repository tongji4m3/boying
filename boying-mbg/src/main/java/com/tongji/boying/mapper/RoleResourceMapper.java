package com.tongji.boying.mapper;

import com.tongji.boying.model.RoleResource;
import com.tongji.boying.model.RoleResourceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleResourceMapper
{
    long countByExample(RoleResourceExample example);

    int deleteByExample(RoleResourceExample example);

    int deleteByPrimaryKey(Integer roleResourceId);

    int insert(RoleResource record);

    int insertSelective(RoleResource record);

    List<RoleResource> selectByExample(RoleResourceExample example);

    RoleResource selectByPrimaryKey(Integer roleResourceId);

    int updateByExampleSelective(@Param("record") RoleResource record, @Param("example") RoleResourceExample example);

    int updateByExample(@Param("record") RoleResource record, @Param("example") RoleResourceExample example);

    int updateByPrimaryKeySelective(RoleResource record);

    int updateByPrimaryKey(RoleResource record);
}
