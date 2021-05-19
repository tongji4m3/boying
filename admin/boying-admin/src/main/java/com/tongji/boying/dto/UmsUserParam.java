package com.tongji.boying.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class UmsUserParam
{
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "个人头像")
    private String icon;

    @ApiModelProperty(value = "用户真实姓名")
    private String realName;

    @ApiModelProperty(value = "用户身份证号")
    private String identityNumber;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "用户性别 1->男,0->女")
    private Boolean gender;

    @ApiModelProperty(value = "管理员是否删除了该用户,默认为0，即未删除状态")
    private Boolean adminDelete;
}
