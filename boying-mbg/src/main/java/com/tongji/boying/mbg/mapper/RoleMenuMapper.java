package com.tongji.boying.mbg.mapper;

import com.tongji.boying.mbg.model.RoleMenu;
import com.tongji.boying.mbg.model.RoleMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMenuMapper {
    long countByExample(RoleMenuExample example);

    int deleteByExample(RoleMenuExample example);

    int deleteByPrimaryKey(Integer roleMenuId);

    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);

    List<RoleMenu> selectByExample(RoleMenuExample example);

    RoleMenu selectByPrimaryKey(Integer roleMenuId);

    int updateByExampleSelective(@Param("record") RoleMenu record, @Param("example") RoleMenuExample example);

    int updateByExample(@Param("record") RoleMenu record, @Param("example") RoleMenuExample example);

    int updateByPrimaryKeySelective(RoleMenu record);

    int updateByPrimaryKey(RoleMenu record);
}