package com.tongji.boying.mapper;

import com.tongji.boying.model.BoyingSeat;
import com.tongji.boying.model.BoyingSeatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoyingSeatMapper {
    long countByExample(BoyingSeatExample example);

    int deleteByExample(BoyingSeatExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BoyingSeat record);

    int insertSelective(BoyingSeat record);

    List<BoyingSeat> selectByExample(BoyingSeatExample example);

    BoyingSeat selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BoyingSeat record, @Param("example") BoyingSeatExample example);

    int updateByExample(@Param("record") BoyingSeat record, @Param("example") BoyingSeatExample example);

    int updateByPrimaryKeySelective(BoyingSeat record);

    int updateByPrimaryKey(BoyingSeat record);
}