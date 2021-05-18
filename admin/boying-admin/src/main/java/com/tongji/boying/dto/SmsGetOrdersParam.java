package com.tongji.boying.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SmsGetOrdersParam {

    @ApiModelProperty("分页的第几页(0或null为不添加该约束,即查询第一页)(0和1都代表第一页)")
    private Integer pageNum;

    @ApiModelProperty("每页大小(0或null为不添加该约束,则默认大小为5)")
    private Integer pageSize;

    @ApiModelProperty("根据用户id查询")
    private Integer userId;

    @ApiModelProperty("根据演出id查询")
    private Integer showId;
}
