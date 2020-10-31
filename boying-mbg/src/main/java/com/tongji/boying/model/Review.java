package com.tongji.boying.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class Review implements Serializable {
    private Integer reviewId;

    @ApiModelProperty(value = "该评论所属用户Id")
    private Integer userId;

    @ApiModelProperty(value = "该评论所属演出Id")
    private Integer showId;

    @ApiModelProperty(value = "对演出打出的星级")
    private Integer star;

    @ApiModelProperty(value = "评论时间")
    private Date time;

    @ApiModelProperty(value = "点赞数量")
    private Integer likeCount;

    @ApiModelProperty(value = "举报数量")
    private Integer reportCount;

    @ApiModelProperty(value = "如果是0则是单独评论,否则是对某个影评的评论")
    private Integer parentId;

    @ApiModelProperty(value = "评论权重,管理员操作,你懂的")
    private Integer weight;

    @ApiModelProperty(value = "评论内容")
    private byte[] content;

    private static final long serialVersionUID = 1L;

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
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

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getReportCount() {
        return reportCount;
    }

    public void setReportCount(Integer reportCount) {
        this.reportCount = reportCount;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", reviewId=").append(reviewId);
        sb.append(", userId=").append(userId);
        sb.append(", showId=").append(showId);
        sb.append(", star=").append(star);
        sb.append(", time=").append(time);
        sb.append(", likeCount=").append(likeCount);
        sb.append(", reportCount=").append(reportCount);
        sb.append(", parentId=").append(parentId);
        sb.append(", weight=").append(weight);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}