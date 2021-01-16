package com.tongji.boying.dto.adminParam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class UsernameLoginParam {
    @ApiModelProperty(value = "username")
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "password")
    @NotEmpty(message = "密码不能为空")
    private String password;
}
