package com.tongji.boying.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
public class UserOrderParam {
    @ApiModelProperty(value = "场次Id")
    @NotNull(message = "场次Id不能为空")
    private Integer showSessionId;

    @ApiModelProperty(value = "座次Id的集合")
    private List<Integer> showClassIds;

    @ApiModelProperty(value = "支付方式")
    @NotNull(message = "支付方式不能为空")
    private String payment;
}

