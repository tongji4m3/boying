package com.tongji.boying.dto.showParam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ToString
public class ShowParam {
    @ApiModelProperty("演出名称关键词,可模糊查询(空字符串或null为不添加该约束)")
    private String keyword;

    @ApiModelProperty("演出所在城市(空字符串或null为不添加该约束,即全国)")
    private String city;

    @ApiModelProperty("演出目录Id(0或null为不添加该约束,即查询出所有演出)")
    private Integer categoryId;

    @ApiModelProperty("分页的第几页(0或null为不添加该约束,即查询第一页)(0和1都代表第一页)")
    private Integer pageNum;

    @ApiModelProperty("每页大小(0或null为不添加该约束,则默认大小为5)")
    private Integer pageSize;

    @ApiModelProperty("排序字段:1->按推荐降序；2->按时间(按照开始时间降序)；3->最低价格从低到高；4->最低价格从高到低(0或null为不添加该约束,则默认为1)")
    private Integer sort;

    @ApiModelProperty("演出开始时间、结束时间在此date之间的(null为不添加该约束)")
    private Date date;

}
