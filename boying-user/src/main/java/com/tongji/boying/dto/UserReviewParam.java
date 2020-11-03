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
public class UserReviewParam
{
    @ApiModelProperty(value = "评价对应的演出")
    @NotNull(message = "评价对应的演出不能为空")
    private Integer showId;

    @ApiModelProperty(value = "评价星级")
    @NotNull(message = "评价星级不能为空")
    private Integer star;

    @ApiModelProperty(value = "评价内容")
    @NotEmpty(message = "评价内容不能为空")
    private String content;

}

