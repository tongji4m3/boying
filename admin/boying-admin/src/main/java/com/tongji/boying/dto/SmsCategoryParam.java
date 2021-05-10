package com.tongji.boying.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SmsCategoryParam
{


    @ApiModelProperty(value = "目录名称")
    private String name;

    @ApiModelProperty(value = "用于排序,0则不显示")
    private Integer weight;

    @ApiModelProperty(value = "该目录的图标")
    private String icon;

    @ApiModelProperty(value = "目录描述")
    private String description;

    @ApiModelProperty(value = "管理员是否删除")
    private boolean adminDelete;
}
