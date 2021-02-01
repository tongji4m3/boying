package com.tongji.boying.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoyingSeatModel {
    private Integer id;

    @ApiModelProperty(value = "所属演出Id")
    private Integer showId;

    @ApiModelProperty(value = "所属哪个级别,例如'A等票“，”B等票“")
    private String name;

    @ApiModelProperty(value = "该级别座位的定价")
    private Double price;

    @ApiModelProperty(value = "该级别座位的容量")
    private Integer capacity;

    @ApiModelProperty(value = "剩余的库存")
    private Integer stock;

    //使用聚合模型，如果promoModel不为空，则表示其拥有还未结束的秒杀活动
    private BoyingPromoModel boyingPromoModel;

}
