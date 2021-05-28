package com.tongji.boying.dto.showParam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ShowParam {
    @ApiModelProperty("演出名称关键词,可模糊查询(空字符串或null为不添加该约束)")
    private String keyword;

    @ApiModelProperty("演出所在城市(空字符串或null或全国 为不添加该约束,即全国)")
    private String city;

    @ApiModelProperty("演出目录Id(0或null为不添加该约束,即查询出所有演出)")
    private Integer categoryId;

    @ApiModelProperty("分页的第几页(0或null为不添加该约束,即查询第一页)(0和1都代表第一页)")
    private Integer pageNum;

    @ApiModelProperty("每页大小(0或null为不添加该约束,则默认大小为5)")
    private Integer pageSize;
}
