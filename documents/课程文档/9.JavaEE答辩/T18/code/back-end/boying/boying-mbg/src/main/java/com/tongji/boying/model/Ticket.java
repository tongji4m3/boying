package com.tongji.boying.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer ticketId;
    @ApiModelProperty(value = "所属订单Id")
    private Integer orderId;
    @ApiModelProperty(value = "所属座次Id")
    private Integer showClassId;
    @ApiModelProperty(value = "二维码图片,供观影人验证入场")
    private String qrCodeUrl;

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getShowClassId() {
        return showClassId;
    }

    public void setShowClassId(Integer showClassId) {
        this.showClassId = showClassId;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ticketId=").append(ticketId);
        sb.append(", orderId=").append(orderId);
        sb.append(", showClassId=").append(showClassId);
        sb.append(", qrCodeUrl=").append(qrCodeUrl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
