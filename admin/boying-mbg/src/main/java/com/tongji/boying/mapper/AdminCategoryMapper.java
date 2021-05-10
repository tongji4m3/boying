package com.tongji.boying.mapper;

import com.tongji.boying.model.AdminCategory;
import com.tongji.boying.model.AdminCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminCategoryMapper {
    long countByExample(AdminCategoryExample example);

    int deleteByExample(AdminCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdminCategory record);

    int insertSelective(AdminCategory record);

    List<AdminCategory> selectByExample(AdminCategoryExample example);

    AdminCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdminCategory record, @Param("example") AdminCategoryExample example);

    int updateByExample(@Param("record") AdminCategory record, @Param("example") AdminCategoryExample example);

    int updateByPrimaryKeySelective(AdminCategory record);

    int updateByPrimaryKey(AdminCategory record);
}