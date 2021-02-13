package com.tongji.boying.dto.userParam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class UpdateInfoParam {
    @ApiModelProperty(value = "password")
    private String realName;

    @ApiModelProperty(value = "identityNumber")
    private String identityNumber;

    @ApiModelProperty(value = "email")
    private String email;

    @ApiModelProperty(value = "icon")
    private String icon;

    @ApiModelProperty(value = "age")
    private Integer age;

    @ApiModelProperty(value = "gender")
    private Boolean gender;
}
