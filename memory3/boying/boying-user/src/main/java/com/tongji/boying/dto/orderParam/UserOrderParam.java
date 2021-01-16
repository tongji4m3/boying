package com.tongji.boying.dto.orderParam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

    @ApiModelProperty(value = "订单支付方式")
    @NotEmpty(message = "订单支付方式不能为空!")
    private String payment;

    @ApiModelProperty(value = "演出座次Id的集合")
    private List<Integer> showSeatIds;
}

