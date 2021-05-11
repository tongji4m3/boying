package com.tongji.boying.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class SmsSeatParam {

    @ApiModelProperty(value = "所属演出Id")
    @NotEmpty(message = "所属演出Id不能为空")
    private Integer showId;

    @ApiModelProperty(value = "所属哪个级别,例如'A等票“，”B等票“")
    @NotEmpty(message = "所属哪个级别不能为空")
    private String name;

    @ApiModelProperty(value = "该级别座位的定价")
    @NotEmpty(message = "该级别座位的定价不能为空")
    private Double price;

    @ApiModelProperty(value = "该级别座位的容量")
    @NotEmpty(message = "该级别座位的容量不能为空")
    private Integer capacity;
}
