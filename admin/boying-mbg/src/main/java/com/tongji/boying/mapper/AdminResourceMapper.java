package com.tongji.boying.mapper;

import com.tongji.boying.model.AdminResource;
import com.tongji.boying.model.AdminResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminResourceMapper {
    long countByExample(AdminResourceExample example);

    int deleteByExample(AdminResourceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdminResource record);

    int insertSelective(AdminResource record);

    List<AdminResource> selectByExample(AdminResourceExample example);

    AdminResource selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdminResource record, @Param("example") AdminResourceExample example);

    int updateByExample(@Param("record") AdminResource record, @Param("example") AdminResourceExample example);

    int updateByPrimaryKeySelective(AdminResource record);

    int updateByPrimaryKey(AdminResource record);
}