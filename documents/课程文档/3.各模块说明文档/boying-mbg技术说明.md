# Mybatis generator

MyBatis的代码生成器，通常情况下的单表查询不用再手写mapper。

MyBatis Generator 生成的文件包含三类：

（1）Model实体文件，一个数据库表对应生成一个 Model 实体与一个Example文件；
		（2）Mapper接口文件，数据数操作方法都在此接口中定义；
		（3）Mapper XML配置文件

### generator.properties

配置数据库的连接信息,之后就将生成该数据库的相关model与mapper

### generatorConfig.xml

Mybatis generator的一些配置信息

### CommentGenerator

写一个新类CommentGenerator实现`DefaultCommentGenerator`接口的方法来使自动生成的model类含有中文注释

### Generator

运行Generator的main函数生成代码,指定生成model,mapper接口,mapper.xml的路径

## 生成文件
### model

基本就是数据库中的字段信息,把数据库的字段注释也生成了。同时还生成Setter，Getter，toString方法

### example

| 方法                                       | 说明                                          |
| ------------------------------------------ | --------------------------------------------- |
| example.setOrderByClause(“字段名 ASC”)     | 添加升序排列条件，DESC为降序                  |
| example.setDistinct(false)                 | 去除重复，boolean型，true为选择不重复的记录。 |
| criteria.andXxxIsNull                      | 添加字段xxx为null的条件                       |
| criteria.andXxxIsNotNull                   | 添加字段xxx不为null的条件                     |
| criteria.andXxxEqualTo(value)              | 添加xxx字段等于value条件                      |
| criteria.andXxxNotEqualTo(value)           | 添加xxx字段不等于value条件                    |
| criteria.andXxxGreaterThan(value)          | 添加xxx字段大于value条件                      |
| criteria.andXxxGreaterThanOrEqualTo(value) | 添加xxx字段大于等于value条件                  |
| criteria.andXxxLessThan(value)             | 添加xxx字段小于value条件                      |
| criteria.andXxxLessThanOrEqualTo(value)    | 添加xxx字段小于等于value条件                  |
| criteria.andXxxIn(List<？>)                | 添加xxx字段值在List<？>条件                   |
| criteria.andXxxNotIn(List<？>)             | 添加xxx字段值不在List<？>条件                 |
| criteria.andXxxLike(“%”+value+”%”)         | 添加xxx字段值为value的模糊查询条件            |
| criteria.andXxxNotLike(“%”+value+”%”)      | 添加xxx字段值不为value的模糊查询条件          |

实例对应的example，example用于添加条件，相当where后面的部分,常用写法为：

```java
UserExample example = new UserExample();
UserExample.Criteria criteria = example.createCriteria();
criteria.andUsernameEqualTo("a%");
example.setOrderByClause("desc");
List<User> userList = userMapper.selectByExample(example);

```

### mapper

```java
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

```



## bug解决


1. `The specified target project directory boying does not exist`.解决方案:将`boying/src/main/java`改为`src/main/java`
2. `closing inbound before receiving peer's close_notify`.解决方案:在配置文件`generator.properties`的`connectionURL`最后加上`&useSSL=false`
3. `Result Maps collection already contains value for com.tongji.mbg.mapper.UserMapper.BaseResultMap`删除原来生成的文件,重新逆向工程构建mapper等

