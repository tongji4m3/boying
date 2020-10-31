package com.tongji.boying.dto;

import com.tongji.boying.model.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@ToString
public class RoleParam
{
    @ApiModelProperty(value = "角色名称",required = true)
    @NotEmpty(message = "角色名称不能为空")
    private String name;
    @ApiModelProperty(value = "角色描述",required = true)
    private String description;

    @ApiModelProperty(value = "启用状态：0->禁用；1->启用")
    private Boolean status;

    @ApiModelProperty(value = "用于排序")
    private Integer sort;
}
