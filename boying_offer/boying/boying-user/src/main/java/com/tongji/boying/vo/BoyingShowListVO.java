package com.tongji.boying.vo;

import com.tongji.boying.model.BoyingCategory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class BoyingShowListVO {
    private Integer id;

    private String name;

    @ApiModelProperty(value = "该演唱会目录相关信息")
    private BoyingCategory category;

    @ApiModelProperty(value = "该演唱会具体图文信息")
    private String details;

    @ApiModelProperty(value = "该演唱会的海报信息")
    private String poster;

    @ApiModelProperty(value = "演出所在城市")
    private String city;

    @ApiModelProperty(value = "具体演出地址")
    private String address;

    @ApiModelProperty(value = "演出开始时间")
    private Date startTime;

    @ApiModelProperty(value = "演出结束时间")
    private Date endTime;

    @ApiModelProperty(value = "演出 最低价")
    private Double minPrice;

    @ApiModelProperty(value = "演出 最高价")
    private Double maxPrice;
}
