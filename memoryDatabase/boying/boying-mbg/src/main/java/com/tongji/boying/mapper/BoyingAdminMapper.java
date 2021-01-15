package com.tongji.boying.mapper;

import com.tongji.boying.model.BoyingAdmin;
import com.tongji.boying.model.BoyingAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BoyingAdminMapper {
    long countByExample(BoyingAdminExample example);

    int deleteByExample(BoyingAdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BoyingAdmin record);

    int insertSelective(BoyingAdmin record);

    List<BoyingAdmin> selectByExample(BoyingAdminExample example);

    BoyingAdmin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BoyingAdmin record, @Param("example") BoyingAdminExample example);

    int updateByExample(@Param("record") BoyingAdmin record, @Param("example") BoyingAdminExample example);

    int updateByPrimaryKeySelective(BoyingAdmin record);

    int updateByPrimaryKey(BoyingAdmin record);
}
