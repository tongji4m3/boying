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
    private Integer showId;
    private String name;
    private Double price;
    private Integer capacity;
    private Integer stock;
    private BoyingPromoModel boyingPromoModel; // 使用聚合模型，如果promoModel不为空，则表示其拥有还未结束的秒杀活动
}
