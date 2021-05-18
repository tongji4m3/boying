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
public class UmsPromoParam {
    @ApiModelProperty(value="活动的座位的id",required = true)
    private Integer seatId;

    @ApiModelProperty(value="活动名称",required = true)
    private String name;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "活动开始时间",required = true)
    private Date startTime;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "活动结束时间",required = true)
    private Date endTime;

    @ApiModelProperty(value = "价格",required = true)
    private double price;

    public String getName(){
        return name;
    }
}
