package com.tongji.mbg.mapper;

import com.tongji.mbg.model.Rolepermission;
import com.tongji.mbg.model.RolepermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolepermissionMapper {
    long countByExample(RolepermissionExample example);

    int deleteByExample(RolepermissionExample example);

    int deleteByPrimaryKey(Integer rolepermissionid);

    int insert(Rolepermission record);

    int insertSelective(Rolepermission record);

    List<Rolepermission> selectByExample(RolepermissionExample example);

    Rolepermission selectByPrimaryKey(Integer rolepermissionid);

    int updateByExampleSelective(@Param("record") Rolepermission record, @Param("example") RolepermissionExample example);

    int updateByExample(@Param("record") Rolepermission record, @Param("example") RolepermissionExample example);

    int updateByPrimaryKeySelective(Rolepermission record);

    int updateByPrimaryKey(Rolepermission record);
}