package com.tongji.boying.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 演出场次参数
 */
@Getter
@Setter
@ToString
public class SmsSessionParam
{
    @ApiModelProperty(value = "所属演唱会Id")
    private Integer showId;

    @ApiModelProperty(value = "演出场次开始时间")
    private Date startTime;

    @ApiModelProperty(value = "演出场次结束时间")
    private Date endTime;

    @ApiModelProperty(value = "上映后,已下架等,以及显示的优先级")
    private Integer weight;
}
