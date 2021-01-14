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
public class WebLog {
    private String description;
    private Long startTime;
    private Integer spendTime;
    /**
     * 获取用户请求的url
     */
    private String url;
    /**
     * 获取用户请求的方式 POST/GET
     */
    private String method;
    /**
     * 获取用户请求参数
     */
    private Object parameter;
    /**
     * 获取给用户的返回类型
     */
    private Object result;
}
