package com.tongji.boying.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class SmsCategoryParam {
    @ApiModelProperty(value = "目录名称")
    @NotEmpty(message = "目录名称不能为空")
    private String name;

    @ApiModelProperty(value = "用于排序")
    private Integer weight;

    @ApiModelProperty(value = "该目录的图标")
    private String icon;

    @ApiModelProperty(value = "目录描述")
    private String description;
}
