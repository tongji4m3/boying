package com.tongji.boying.dto.userParam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class UserRegisterParam {
    @ApiModelProperty("username")
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @ApiModelProperty("password")
    @NotEmpty(message = "密码不能为空")
    private String password;

    @ApiModelProperty("telephone")
    @NotEmpty(message = "手机号不能为空")
    private String telephone;

    @ApiModelProperty("authCode")
    @NotEmpty(message = "验证码不能为空")
    private String authCode;

    @ApiModelProperty("icon")
    private String icon;
}
