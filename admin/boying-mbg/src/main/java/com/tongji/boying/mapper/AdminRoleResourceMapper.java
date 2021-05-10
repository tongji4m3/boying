package com.tongji.boying.mapper;

import com.tongji.boying.model.AdminRoleResource;
import com.tongji.boying.model.AdminRoleResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRoleResourceMapper {
    long countByExample(AdminRoleResourceExample example);

    int deleteByExample(AdminRoleResourceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdminRoleResource record);

    int insertSelective(AdminRoleResource record);

    List<AdminRoleResource> selectByExample(AdminRoleResourceExample example);

    AdminRoleResource selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdminRoleResource record, @Param("example") AdminRoleResourceExample example);

    int updateByExample(@Param("record") AdminRoleResource record, @Param("example") AdminRoleResourceExample example);

    int updateByPrimaryKeySelective(AdminRoleResource record);

    int updateByPrimaryKey(AdminRoleResource record);
}