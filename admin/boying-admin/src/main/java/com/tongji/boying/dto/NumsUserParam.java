package com.tongji.boying.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class NumsUserParam {
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "用户真实姓名")
    private String realName;

    @ApiModelProperty(value = "用户身份证号")
    private String identityNumber;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "用户性别 1->男,0->女")
    private Boolean gender;

    @ApiModelProperty(value = "帐号启用状态：0->禁用；1->启用")
    private Boolean userstatus;

    @ApiModelProperty(value = "个人头像")
    private String icon;

    @ApiModelProperty(value = "默认观影者")
    private Integer defaultFrequent;

    @ApiModelProperty(value = "默认地址")
    private Integer defaultAddress;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
