package com.tongji.boying.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author cheng fu
 * @Description TODO
 * @date 2020/11/1-21:44
 */
@Getter
@Setter
@ToString
public class UmsShowParam
{
    private String name;
    private int categoryId;
    private String poster;
    private String details;
    private double minPrice;
    private double maxPrice;
    private int weight;
    private String city;
    private String address;
    private Date dayStart;
    private Date dayEnd;
}
