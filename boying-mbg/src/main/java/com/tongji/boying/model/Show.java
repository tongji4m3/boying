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
        sb.append(", details=").append(details);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}