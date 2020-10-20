package com.tongji.boying.common.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Controller层的日志封装类
 * lombok注解中@Data注解包含了get，set和toString方法
 * 其中@EqualsAndHashCode(callSuper = false)使得若不调用父类的属性
 * 那么子类属性里面的相同的话，那hashcode的值就相同
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WebLog
{
    /**
     * 操作描述
     */
    private String description;

    /**
     * 操作用户
     */
    private String username;

    /**
     * 操作时间
     */
    private Long startTime;

    /**
     * 消耗时间
     */
    private Integer spendTime;

    /**
     * 根路径
     */
    private String basePath;

    /**
     * URI
     */
    private String uri;

    /**
     * URL
     */
    private String url;

    /**
     * 请求类型
     */
    private String method;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 请求参数
     */
    private Object parameter;

    /**
     * 返回结果
     */
    private Object result;

}
