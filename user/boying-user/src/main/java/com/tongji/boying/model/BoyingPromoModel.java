package com.tongji.boying.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString
public class BoyingPromoModel {
    private Integer id;
    private Integer status; // 秒杀活动状态 0表示没有秒杀活动 1表示还未开始，2表示进行中，3表示已结束
    private Integer seatId;
    private String name;
    private Date startTime;
    private Date endTime;
    private Double price;

}
