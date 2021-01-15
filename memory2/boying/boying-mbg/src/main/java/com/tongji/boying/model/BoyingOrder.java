package com.tongji.boying.model;

import java.util.Date;

public class BoyingOrder {
    private Integer id;

    private Integer userId;

    private Integer showId;

    private Integer status;

    private Date time;

    private Short userDelete;

    private Short adminDelete;

    private Integer ticketCount;

    private Double money;

    private String payment;

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

    public Short getUserDelete() {
        return userDelete;
    }

    public void setUserDelete(Short userDelete) {
        this.userDelete = userDelete;
    }

    public Short getAdminDelete() {
        return adminDelete;
    }

    public void setAdminDelete(Short adminDelete) {
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
        this.payment = payment == null ? null : payment.trim();
    }
}