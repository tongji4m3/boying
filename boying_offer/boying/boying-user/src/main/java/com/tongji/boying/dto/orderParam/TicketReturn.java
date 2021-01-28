package com.tongji.boying.dto.orderParam;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TicketReturn {
    private Integer id;

    private Integer orderId;

    private Integer seatId;

    private String qrCodeUrl;

    private String name;

    private Double price;

    private Integer capacity;

    private Integer stock;
}
