package com.tongji.boying.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class UmsAllocMenuParam
{
    @ApiModelProperty(value = "roleId", required = true)
    @NotEmpty(message = "roleId不能为空")
    private Integer roleId;
    @ApiModelProperty(value = "menuIds", required = true)
    @NotEmpty(message = "menuIds不能为空")
    private List<Integer> menuIds;
}
