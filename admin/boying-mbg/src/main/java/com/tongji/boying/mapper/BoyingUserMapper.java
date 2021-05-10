package com.tongji.boying.mapper;

import com.tongji.boying.model.BoyingUser;
import com.tongji.boying.model.BoyingUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoyingUserMapper {
    long countByExample(BoyingUserExample example);

    int deleteByExample(BoyingUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BoyingUser record);

    int insertSelective(BoyingUser record);

    List<BoyingUser> selectByExample(BoyingUserExample example);

    BoyingUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BoyingUser record, @Param("example") BoyingUserExample example);

    int updateByExample(@Param("record") BoyingUser record, @Param("example") BoyingUserExample example);

    int updateByPrimaryKeySelective(BoyingUser record);

    int updateByPrimaryKey(BoyingUser record);
}