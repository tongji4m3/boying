package com.tongji.boying.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class Frequent implements Serializable {
    private Integer frequentId;

    private Integer userId;

    private String name;

    @ApiModelProperty(value = "常用购票人的身份证号码")
    private String identityNumber;

    private String phone;

    private static final long serialVersionUID = 1L;

    public Integer getFrequentId() {
        return frequentId;
    }

    public void setFrequentId(Integer frequentId) {
        this.frequentId = frequentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", frequentId=").append(frequentId);
        sb.append(", userId=").append(userId);
        sb.append(", name=").append(name);
        sb.append(", identityNumber=").append(identityNumber);
        sb.append(", phone=").append(phone);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}