package com.tongji.boying.dto.showParam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class ShowSeatAddParam {
    @ApiModelProperty(value = "所属演出Id")
    @NotNull(message = "必须传入所属演出Id")
    private Integer showId;

    @ApiModelProperty(value = "所属哪个级别,例如'A等票“，”B等票“")
    @NotEmpty(message = "必须传入该级别的名称")
    private String name;

    @ApiModelProperty(value = "该级别座位的定价")
    @NotNull(message = "必须传入该级别座位的定价")
    private Double price;

    @ApiModelProperty(value = "该级别座位的容量")
    @NotNull(message = "必须传入该级别座位的容量")
    private Integer capacity;

    @ApiModelProperty(value = "库存,stock不能超出容量，也不能小于0;默认等于容量")
    private Integer stock;
}
