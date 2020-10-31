package com.tongji.boying.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class Session implements Serializable {
    private Integer sessionId;

    @ApiModelProperty(value = "所属演唱会Id")
    private Integer showId;

    @ApiModelProperty(value = "演出场次开始时间")
    private Date startTime;

    @ApiModelProperty(value = "演出场次结束时间")
    private Date endTime;

    @ApiModelProperty(value = "上映后,已下架等,以及显示的优先级")
    private Integer weight;

    private static final long serialVersionUID = 1L;

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getShowId() {
        return showId;
    }

    public void setShowId(Integer showId) {
        this.showId = showId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sessionId=").append(sessionId);
        sb.append(", showId=").append(showId);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", weight=").append(weight);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}