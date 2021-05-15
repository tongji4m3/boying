package com.tongji.boying.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class UmsAllocRoleParam
{
    @ApiModelProperty(value = "userId", required = true)
    @NotEmpty(message = "userId不能为空")
    private Integer userId;
    @ApiModelProperty(value = "roleIds", required = true)
    @NotEmpty(message = "roleIds不能为空")
    private List<Integer> roleIds;
}
