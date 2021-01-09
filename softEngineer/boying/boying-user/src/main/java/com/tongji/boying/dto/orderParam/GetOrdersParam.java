package com.tongji.boying.dto.orderParam;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetOrdersParam {
    @ApiModelProperty("0或null为不添加该约束,则查询所有类型的订单(待观看，已完成，已取消(1，2，3))")
    private Integer status;

    @ApiModelProperty("分页的第几页(0或null为不添加该约束,即查询第一页)(0和1都代表第一页)")
    private Integer pageNum;

    @ApiModelProperty("每页大小(0或null为不添加该约束,则默认大小为5)")
    private Integer pageSize;
}
