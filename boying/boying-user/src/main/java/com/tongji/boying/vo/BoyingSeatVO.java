package com.tongji.boying.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class BoyingSeatVO {
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

    //记录商品是否在秒杀活动中，以及对应的状态：0表示没有秒杀活动，1表示秒杀活动待开始，2表示秒杀活动进行中
    private Integer promoStatus;

    //秒杀活动价格
    private double promoPrice;

    //秒杀活动ID
    private Integer promoId;

    //秒杀活动开始时间
    private Date startTime;
}
