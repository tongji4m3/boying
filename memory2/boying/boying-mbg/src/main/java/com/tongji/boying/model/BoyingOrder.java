package com.tongji.boying.model;

import java.math.BigDecimal;
import java.util.Date;

public class BoyingOrder {
    private Long id;

    private Long userId;

    private Long showId;

    private Long status;

    private Date time;

    private String userDelete;

    private String adminDelete;

    private Long ticketCount;

    private BigDecimal money;

    private String payment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUserDelete() {
        return userDelete;
    }

    public void setUserDelete(String userDelete) {
        this.userDelete = userDelete == null ? null : userDelete.trim();
    }

    public String getAdminDelete() {
        return adminDelete;
    }

    public void setAdminDelete(String adminDelete) {
        this.adminDelete = adminDelete == null ? null : adminDelete.trim();
    }

    public Long getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(Long ticketCount) {
        this.ticketCount = ticketCount;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment == null ? null : payment.trim();
    }
}