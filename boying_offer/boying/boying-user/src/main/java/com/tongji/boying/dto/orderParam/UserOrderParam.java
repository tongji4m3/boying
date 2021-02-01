package com.tongji.boying.dto.orderParam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
public class UserOrderParam {
    @ApiModelProperty(value = "showId")
    @NotNull(message = "演出Id不能为空")
    private Integer showId;

    @ApiModelProperty(value = "seatId")
    @NotNull(message = "演出座次Id不能为空")
    private Integer seatId;

    @ApiModelProperty(value = "promoId")
    private Integer promoId;

    @ApiModelProperty(value = "购买的票数（最多三张)")
    @NotNull(message = "购买的演出票数不能为空")
    @Max(3)
    @Min(1)
    private Integer count;

    @ApiModelProperty(value = "订单支付方式")
    @NotEmpty(message = "订单支付方式不能为空!")
    private String payment;
}

