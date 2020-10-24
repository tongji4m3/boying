package com.tongji.boying.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class Ticket implements Serializable {
    private Integer ticketId;

    private Integer frequentId;

    private Integer showClassId;

    private Integer orderId;

    private static final long serialVersionUID = 1L;

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getFrequentId() {
        return frequentId;
    }

    public void setFrequentId(Integer frequentId) {
        this.frequentId = frequentId;
    }

    public Integer getShowClassId() {
        return showClassId;
    }

    public void setShowClassId(Integer showClassId) {
        this.showClassId = showClassId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ticketId=").append(ticketId);
        sb.append(", frequentId=").append(frequentId);
        sb.append(", showClassId=").append(showClassId);
        sb.append(", orderId=").append(orderId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}