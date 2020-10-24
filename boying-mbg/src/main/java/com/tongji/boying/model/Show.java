package com.tongji.boying.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

public class Show implements Serializable {
    private Integer showId;

    private String name;

    @ApiModelProperty(value = "所属的目录")
    private Integer categoryId;

    @ApiModelProperty(value = "该演唱会的海报信息")
    private String poster;

    @ApiModelProperty(value = "该演唱会的最低价")
    private BigDecimal minPrice;

    @ApiModelProperty(value = "该演出展示的优先基本,0为不展示")
    private Integer weight;

    private String city;

    @ApiModelProperty(value = "具体演出地址")
    private String address;

    @ApiModelProperty(value = "价格区间(文本描述)")
    private String priceRange;

    @ApiModelProperty(value = "演出时间区间(文本描述)")
    private String timeRange;

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

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public String getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(String timeRange) {
        this.timeRange = timeRange;
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
        sb.append(", weight=").append(weight);
        sb.append(", city=").append(city);
        sb.append(", address=").append(address);
        sb.append(", priceRange=").append(priceRange);
        sb.append(", timeRange=").append(timeRange);
        sb.append(", details=").append(details);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}