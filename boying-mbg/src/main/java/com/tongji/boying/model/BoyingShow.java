package com.tongji.boying.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BoyingShow implements Serializable {
    private Integer showId;

    private String name;

    @ApiModelProperty(value = "所属的目录")
    private Integer categoryId;

    @ApiModelProperty(value = "该演唱会的海报信息")
    private String poster;

    @ApiModelProperty(value = "该演唱会的最低价")
    private BigDecimal minPrice;

    @ApiModelProperty(value = "该演唱会的最高价")
    private BigDecimal maxPrice;

    @ApiModelProperty(value = "该演出展示的优先基本,0为不展示")
    private Integer weight;

    private String city;

    @ApiModelProperty(value = "具体演出地址")
    private String address;

    @ApiModelProperty(value = "演出开始日期")
    private Date dayStart;

    @ApiModelProperty(value = "演出结束日期")
    private Date dayEnd;

    @ApiModelProperty(value = "存储该演唱会等的图文信息")
    private byte[] details;

    private static final long serialVersionUID = 1L;

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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDayStart() {
        return dayStart;
    }

    public void setDayStart(Date dayStart) {
        this.dayStart = dayStart;
    }

    public Date getDayEnd() {
        return dayEnd;
    }

    public void setDayEnd(Date dayEnd) {
        this.dayEnd = dayEnd;
    }

    public byte[] getDetails() {
        return details;
    }

    public void setDetails(byte[] details) {
        this.details = details;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", showId=").append(showId);
        sb.append(", name=").append(name);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", poster=").append(poster);
        sb.append(", minPrice=").append(minPrice);
        sb.append(", maxPrice=").append(maxPrice);
        sb.append(", weight=").append(weight);
        sb.append(", city=").append(city);
        sb.append(", address=").append(address);
        sb.append(", dayStart=").append(dayStart);
        sb.append(", dayEnd=").append(dayEnd);
        sb.append(", details=").append(details);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}