package com.tongji.boying.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class ShowClass implements Serializable {
    private Integer showClassId;

    @ApiModelProperty(value = "所属场次Id")
    private Integer showSessionId;

    @ApiModelProperty(value = "所属哪个级别,例如'学生单日票', '预售单日票', '全价单日票', 'PRO单日票'等等")
    private String name;

    @ApiModelProperty(value = "该级别座位的容量")
    private Integer capacity;

    @ApiModelProperty(value = "该级别座位的定价")
    private Double price;

    private static final long serialVersionUID = 1L;

    public Integer getShowClassId() {
        return showClassId;
    }

    public void setShowClassId(Integer showClassId) {
        this.showClassId = showClassId;
    }

    public Integer getShowSessionId() {
        return showSessionId;
    }

    public void setShowSessionId(Integer showSessionId) {
        this.showSessionId = showSessionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", showClassId=").append(showClassId);
        sb.append(", showSessionId=").append(showSessionId);
        sb.append(", name=").append(name);
        sb.append(", capacity=").append(capacity);
        sb.append(", price=").append(price);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}