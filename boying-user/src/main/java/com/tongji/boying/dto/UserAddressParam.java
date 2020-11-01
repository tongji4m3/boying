package com.tongji.boying.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class UserAddressParam
{
    @ApiModelProperty(value = "接收人")
    @NotEmpty(message = "接收人不能为空")
    private String receiver;

    @ApiModelProperty(value = "接收电话")
    @NotEmpty(message = "接收电话不能为空")
    private String phone;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "城区")
    private String region;

    @ApiModelProperty(value = "街道")
    private String street;

    @ApiModelProperty(value = "街道后的详细地址")
    private String details;
}

