package com.tongji.boying.dto.orderParam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TicketReturn {
    @ApiModelProperty(value = "所属座次的级别,例如'A等票“，”B等票“")
    private String name;

    @ApiModelProperty(value = "所属座位的定价")
    private Double price;

    @ApiModelProperty(value = "所属座位的容量")
    private Integer capacity;

    @ApiModelProperty(value = "二维码图片,供观影人验证入场")
    private String qrCodeUrl;
}
