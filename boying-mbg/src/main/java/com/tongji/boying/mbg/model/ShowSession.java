package com.tongji.boying.mbg.model;

import java.io.Serializable;
import java.util.Date;

public class ShowSession implements Serializable {
    private Integer showSessionId;

    /**
     * 所属哪个演唱会
     *
     * @mbg.generated
     */
    private Integer showId;

    private String city;

    private Date startTime;

    private Date endTime;

    /**
     * 具体演出地址
     *
     * @mbg.generated
     */
    private String address;

    /**
     * 上映后,已下架等,以及显示的优先级
     *
     * @mbg.generated
     */
    private Integer weight;

    private static final long serialVersionUID = 1L;

    public Integer getShowSessionId() {
        return showSessionId;
    }

    public void setShowSessionId(Integer showSessionId) {
        this.showSessionId = showSessionId;
    }

    public Integer getShowId() {
        return showId;
    }

    public void setShowId(Integer showId) {
        this.showId = showId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        sb.append(", showSessionId=").append(showSessionId);
        sb.append(", showId=").append(showId);
        sb.append(", city=").append(city);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", address=").append(address);
        sb.append(", weight=").append(weight);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}