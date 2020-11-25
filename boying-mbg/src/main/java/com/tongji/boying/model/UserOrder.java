package com.tongji.boying.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class UserOrder implements Serializable {
    private Integer orderId;

    @ApiModelProperty(value = "所属用户Id")
    private Integer userId;

    @ApiModelProperty(value = "所属演出Id")
    private Integer showId;

    @ApiModelProperty(value = "所属场次Id")
    private Integer showSessionId;

    @ApiModelProperty(value = "这些票要邮寄到什么地方")
    private Integer addressId;

    @ApiModelProperty(value = "这些票的实际观演人")
    private Integer frequentId;

    @ApiModelProperty(value = "待评价,已完成,已退订单(1,2,3)")
    private Integer status;

    @ApiModelProperty(value = "订单支付时间")
    private Date time;

    @ApiModelProperty(value = "订单支付方式")
    private String payment;

    @ApiModelProperty(value = "该订单对用户是否可见,即用户是否删除了该订单")
    private Boolean userDelete;

    @ApiModelProperty(value = "订单总金额")
    private Double money;

    @ApiModelProperty(value = "票的总数")
    private Integer ticketCount;

    private static final long serialVersionUID = 1L;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getShowId() {
        return showId;
    }

    public void setShowId(Integer showId) {
        this.showId = showId;
    }

    public Integer getShowSessionId() {
        return showSessionId;
    }

    public void setShowSessionId(Integer showSessionId) {
        this.showSessionId = showSessionId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getFrequentId() {
        return frequentId;
    }

    public void setFrequentId(Integer frequentId) {
        this.frequentId = frequentId;
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

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Boolean getUserDelete() {
        return userDelete;
    }

    public void setUserDelete(Boolean userDelete) {
        this.userDelete = userDelete;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(Integer ticketCount) {
        this.ticketCount = ticketCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderId=").append(orderId);
        sb.append(", userId=").append(userId);
        sb.append(", showId=").append(showId);
        sb.append(", showSessionId=").append(showSessionId);
        sb.append(", addressId=").append(addressId);
        sb.append(", frequentId=").append(frequentId);
        sb.append(", status=").append(status);
        sb.append(", time=").append(time);
        sb.append(", payment=").append(payment);
        sb.append(", userDelete=").append(userDelete);
        sb.append(", money=").append(money);
        sb.append(", ticketCount=").append(ticketCount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}