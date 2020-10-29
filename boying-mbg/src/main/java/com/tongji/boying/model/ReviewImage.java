package com.tongji.boying.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class ReviewImage implements Serializable {
    private Integer reviewImageId;

    private Integer reviewId;

    @ApiModelProperty(value = "图片链接地址")
    private String imageUrl;

    private static final long serialVersionUID = 1L;

    public Integer getReviewImageId() {
        return reviewImageId;
    }

    public void setReviewImageId(Integer reviewImageId) {
        this.reviewImageId = reviewImageId;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", reviewImageId=").append(reviewImageId);
        sb.append(", reviewId=").append(reviewId);
        sb.append(", imageUrl=").append(imageUrl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}