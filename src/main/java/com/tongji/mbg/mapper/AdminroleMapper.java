package com.tongji.mbg.mapper;

import com.tongji.mbg.model.Adminrole;
import com.tongji.mbg.model.AdminroleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminroleMapper {
    long countByExample(AdminroleExample example);

    int deleteByExample(AdminroleExample example);

    int deleteByPrimaryKey(Integer adminroleid);

    int insert(Adminrole record);

    int insertSelective(Adminrole record);

    List<Adminrole> selectByExample(AdminroleExample example);

    Adminrole selectByPrimaryKey(Integer adminroleid);

    int updateByExampleSelective(@Param("record") Adminrole record, @Param("example") AdminroleExample example);

    int updateByExample(@Param("record") Adminrole record, @Param("example") AdminroleExample example);

    int updateByPrimaryKeySelective(Adminrole record);

    int updateByPrimaryKey(Adminrole record);
}