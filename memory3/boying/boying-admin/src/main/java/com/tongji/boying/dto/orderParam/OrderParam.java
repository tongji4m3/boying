package com.tongji.boying.dto.orderParam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class OrderParam {
    @ApiModelProperty(value = "所属用户Id(0或null为不添加该约束)")
    private Integer userId;

    @ApiModelProperty("演出Id(0或null为不添加该约束)")
    private Integer showId;

    @ApiModelProperty("分页的第几页(0或null为不添加该约束,即查询第一页)(0和1都代表第一页)")
    private Integer pageNum;

    @ApiModelProperty("每页大小(0或null为不添加该约束,则默认大小为5)")
    private Integer pageSize;
}
