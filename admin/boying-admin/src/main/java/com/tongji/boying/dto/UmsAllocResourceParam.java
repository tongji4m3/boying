package com.tongji.boying.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class UmsAllocResourceParam
{
    @ApiModelProperty(value = "roleId", required = true)
    @NotEmpty(message = "roleId不能为空")
    private Integer roleId;
    @ApiModelProperty(value = "resourceIds", required = true)
//    @NotEmpty(message = "resourceIds不能为空")
    private List<Integer> resourceIds;
}
