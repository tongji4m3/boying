package com.tongji.boying.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class UserFrequentParam
{
    @ApiModelProperty(value = "真实姓名")
    @NotEmpty(message = "真实姓名不能为空")
    private String name;

    @ApiModelProperty(value = "身份证号码")
    @NotEmpty(message = "身份证号码不能为空")
    private String identityNumber;

    @ApiModelProperty(value = "手机号")
    @NotEmpty(message = "手机号不能为空")
    private String phone;
}

