package com.tongji.boying.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class UmsResourceCategoryParam {
    @ApiModelProperty(value = "资源分类名称", required = true)
    @NotEmpty(message = "资源分类名称不能为空")
    private String name;
    @ApiModelProperty(value = "该资源分类排序级别", required = true)
    private Integer sort;
}
