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
    private Integer showId;

    @ApiModelProperty(value = "所属哪个级别,例如'A等票“，”B等票“")
    private String name;

    @ApiModelProperty(value = "该级别座位的定价")
    private Double price;

    @ApiModelProperty(value = "该级别座位的容量")
    private Integer capacity;
}
