package com.tongji.boying.dto.orderParam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class TestParam {
    @ApiModelProperty(value = "模拟请求的线程数量")
    @NotNull(message = "模拟请求的线程数量不能为空")
    Integer threadCount;

    @ApiModelProperty(value = "每个线程模拟请求的订单数量")
    @NotNull(message = "每个线程模拟请求的订单数量不能为空")
    Integer orderCount;
}
