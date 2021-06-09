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
    private Integer status;
    private Integer seatId;
    private String name;
    private Date startTime;
    private Date endTime;
    private Double price;
}
