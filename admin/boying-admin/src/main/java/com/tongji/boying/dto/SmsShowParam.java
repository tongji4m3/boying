package com.tongji.boying.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
public class SmsShowParam
{
    @ApiModelProperty(value = "演出名称", required = true)
    private String name;

    @ApiModelProperty(value = "所属的目录", required = true)
    private Integer categoryId;

    @ApiModelProperty(value = "该演唱会的海报信息", required = true)
    private String poster;

    @ApiModelProperty(value = "该演唱会的最低价", required = true)
    private double minPrice;

    @ApiModelProperty(value = "该演唱会的最高价", required = true)
    private double maxPrice;

    @ApiModelProperty(value = "该演出展示的优先基本,0为不展示", required = true)
    private Integer weight;

    @ApiModelProperty(value = "演出城市", required = true)
    private String city;

    @ApiModelProperty(value = "具体演出地址", required = true)
    private String address;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "演出开始日期", required = true)
    private Date dayStart;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "演出结束日期", required = true)
    private Date dayEnd;

    @ApiModelProperty(value = "存储该演唱会等的图文信息", required = true)
    private String details;
}
