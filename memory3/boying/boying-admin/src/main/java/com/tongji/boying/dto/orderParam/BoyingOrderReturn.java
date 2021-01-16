package com.tongji.boying.dto.orderParam;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class BoyingOrderReturn {
    private Integer id;

    private Integer userId;

    private Integer showId;

    private Integer status;

    private Date time;

    private Boolean userDelete;

    private Boolean adminDelete;

    private Integer ticketCount;

    private Double money;

    private String payment;
}
