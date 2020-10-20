package com.tongji.boying.mapper;

import com.tongji.boying.model.User;
import com.tongji.boying.model.UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper
{
    //按条件计数
    long countByExample(UserExample example);

//    按条件删除
    int deleteByExample(UserExample example);

    //按主键删除
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

//    insertSelective对应的sql语句加入了NULL校验，即只会插入数据不为null的字段值。
    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer userId);

//    第一个参数 是要修改的部分值组成的对象，其中有些属性为null则表示该项不修改。
//    第二个参数 是一个对应的查询条件的类， 通过这个类可以实现 order by 和一部分的where 条件。
    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

//    更新 新的model中不为空的字段
    int updateByPrimaryKeySelective(User record);

//    会将为空的字段在数据库中置为NULL
    int updateByPrimaryKey(User record);
}
