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
public class ResourceParam
{
    @ApiModelProperty(value = "该资源所属的资源分类", required = true)
    @NotNull(message = "该资源所属的资源分类不能为空")
    private Integer resourceCategoryId;
    @ApiModelProperty(value = "资源名称", required = true)
    @NotEmpty(message = "资源名称不能为空")
    private String name;
    @ApiModelProperty(value = "资源url", required = true)
    @NotEmpty(message = "资源url不能为空")
    private String url;
    @ApiModelProperty(value = "资源描述", required = true)
    private String description;
}
