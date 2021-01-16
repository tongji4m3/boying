package com.tongji.boying.dto.showParam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class BoyingShowReturn {
    private Integer id;

    @ApiModelProperty(value = "所属的目录")
    private Integer categoryId;

    private String name;

    @ApiModelProperty(value = "该演唱会的海报信息")
    private String poster;

    @ApiModelProperty(value = "存储该演唱会等的图文信息")
    private String details;

    @ApiModelProperty(value = "演出所在城市")
    private String city;

    @ApiModelProperty(value = "具体演出地址")
    private String address;

    @ApiModelProperty(value = "演出开始时间")
    private Date startTime;

    @ApiModelProperty(value = "演出结束时间")
    private Date endTime;

    @ApiModelProperty(value = "该演出展示的优先基本,0为不展示")
    private Integer weight;

    @ApiModelProperty(value = "演出 最低价")
    private Double minPrice;

    @ApiModelProperty(value = "演出 最高价")
    private Double maxPrice;


}
