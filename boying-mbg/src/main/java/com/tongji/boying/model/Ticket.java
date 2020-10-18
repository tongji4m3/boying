package com.tongji.boying.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class Ticket implements Serializable {
    private Integer ticketid;

    private Integer frequentid;

    private Integer classid;

    private static final long serialVersionUID = 1L;

    public Integer getTicketid() {
        return ticketid;
    }

    public void setTicketid(Integer ticketid) {
        this.ticketid = ticketid;
    }

    public Integer getFrequentid() {
        return frequentid;
    }

    public void setFrequentid(Integer frequentid) {
        this.frequentid = frequentid;
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ticketid=").append(ticketid);
        sb.append(", frequentid=").append(frequentid);
        sb.append(", classid=").append(classid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}