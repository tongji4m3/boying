package com.tongji.boying.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class UmsMenuParam
{
    @ApiModelProperty(value = "父级ID")
    @NotNull(message = "父级ID不能为空")
    private Integer parentId;

    @ApiModelProperty(value = "菜单名称")
    @NotEmpty(message = "菜单名称不能为空")
    private String title;

    @ApiModelProperty(value = "菜单排序")
    private Integer sort;

    @ApiModelProperty(value = "前端图标")
    private String icon;

    @ApiModelProperty(value = "控制前端是否隐藏")
    private Boolean hidden;
}
