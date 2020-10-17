package com.tongji.boying.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private Integer orderId;

    private Integer showSessionId;

    private Integer userId;

    @ApiModelProperty(value = " 已取消,待付款,待使用,待评价")
    private Integer status;

    private Date time;

    private Integer addressId;

    private static final long serialVersionUID = 1L;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getShowSessionId() {
        return showSessionId;
    }

    public void setShowSessionId(Integer showSessionId) {
        this.showSessionId = showSessionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderId=").append(orderId);
        sb.append(", showSessionId=").append(showSessionId);
        sb.append(", userId=").append(userId);
        sb.append(", status=").append(status);
        sb.append(", time=").append(time);
        sb.append(", addressId=").append(addressId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}