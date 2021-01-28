package com.tongji.boying.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class BoyingOrder implements Serializable {
    private Integer id;

    @ApiModelProperty(value = "所属用户Id")
    private Integer userId;

    @ApiModelProperty(value = "所属演出Id")
    private Integer showId;

    @ApiModelProperty(value = "对应演出座次Id")
    private Integer seatId;

    @ApiModelProperty(value = "待观看，已完成，已取消(1，2，3)")
    private Integer status;

    @ApiModelProperty(value = "订单支付时间")
    private Date time;

    @ApiModelProperty(value = "用户删除")
    private Boolean userDelete;

    @ApiModelProperty(value = "管理员删除")
    private Boolean adminDelete;

    @ApiModelProperty(value = "票的总数")
    private Integer amount;

    @ApiModelProperty(value = "该演出座次的单价")
    private Double ticketPrice;

    @ApiModelProperty(value = "订单总价格")
    private Double orderPrice;

    @ApiModelProperty(value = "订单支付方式（支付宝，微信)")
    private String payment;

    @ApiModelProperty(value = "二维码图片,供观影人验证入场")
    private String qrCodeUrl;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
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

    public Boolean getUserDelete() {
        return userDelete;
    }

    public void setUserDelete(Boolean userDelete) {
        this.userDelete = userDelete;
    }

    public Boolean getAdminDelete() {
        return adminDelete;
    }

    public void setAdminDelete(Boolean adminDelete) {
        this.adminDelete = adminDelete;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
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
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", showId=").append(showId);
        sb.append(", seatId=").append(seatId);
        sb.append(", status=").append(status);
        sb.append(", time=").append(time);
        sb.append(", userDelete=").append(userDelete);
        sb.append(", adminDelete=").append(adminDelete);
        sb.append(", amount=").append(amount);
        sb.append(", ticketPrice=").append(ticketPrice);
        sb.append(", orderPrice=").append(orderPrice);
        sb.append(", payment=").append(payment);
        sb.append(", qrCodeUrl=").append(qrCodeUrl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}