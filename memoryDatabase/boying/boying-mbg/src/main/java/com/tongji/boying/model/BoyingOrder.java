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

    @ApiModelProperty(value = "待观看，已完成，已取消(1，2，3)")
    private Integer status;

    @ApiModelProperty(value = "订单支付时间")
    private Date time;

    @ApiModelProperty(value = "用户删除")
    private Boolean userDelete;

    @ApiModelProperty(value = "管理员删除")
    private Boolean adminDelete;

    @ApiModelProperty(value = "票的总数")
    private Integer ticketCount;

    @ApiModelProperty(value = "订单总价格")
    private Double money;

    @ApiModelProperty(value = "订单支付方式（支付宝，微信)")
    private String payment;

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

    public Integer getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(Integer ticketCount) {
        this.ticketCount = ticketCount;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
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
        sb.append(", status=").append(status);
        sb.append(", time=").append(time);
        sb.append(", userDelete=").append(userDelete);
        sb.append(", adminDelete=").append(adminDelete);
        sb.append(", ticketCount=").append(ticketCount);
        sb.append(", money=").append(money);
        sb.append(", payment=").append(payment);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}