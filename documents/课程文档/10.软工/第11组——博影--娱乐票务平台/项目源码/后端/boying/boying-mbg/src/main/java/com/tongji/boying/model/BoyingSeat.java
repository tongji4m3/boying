package com.tongji.boying.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class BoyingSeat implements Serializable {
    private Integer id;

    @ApiModelProperty(value = "所属演出Id")
    private Integer showId;

    @ApiModelProperty(value = "所属哪个级别,例如'A等票“，”B等票“")
    private String name;

    @ApiModelProperty(value = "该级别座位的定价")
    private Double price;

    @ApiModelProperty(value = "该级别座位的容量")
    private Integer capacity;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShowId() {
        return showId;
    }

    public void setShowId(Integer showId) {
        this.showId = showId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", showId=").append(showId);
        sb.append(", name=").append(name);
        sb.append(", price=").append(price);
        sb.append(", capacity=").append(capacity);
        sb.append(", stock=").append(stock);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}