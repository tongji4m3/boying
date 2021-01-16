package com.tongji.boying.dto.categoryParam;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoyingCategoryReturn {
    private Integer id;

    private String name;

    private String icon;

    private String description;

    private Integer weight;

    private Boolean adminDelete;
}
