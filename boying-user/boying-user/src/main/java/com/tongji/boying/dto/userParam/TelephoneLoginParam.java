package com.tongji.boying.dto.userParam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class TelephoneLoginParam {
    @ApiModelProperty(value = "telephone")
    @NotEmpty(message = "手机号不能为空")
    private String telephone;

    @ApiModelProperty(value = "password")
    @NotEmpty(message = "密码不能为空")
    private String password;
}
