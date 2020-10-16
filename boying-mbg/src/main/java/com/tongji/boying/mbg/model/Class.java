package com.tongji.boying.mbg.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Class implements Serializable {
    private Integer classId;

    private Integer showsessionid;

    /**
     * 所属哪个级别,例如A等座,B等座
     *
     * @mbg.generated
     */
    private Integer grade;

    /**
     * 该级别座位的容量
     *
     * @mbg.generated
     */
    private Integer capacity;

    /**
     * 该级别座位的定价
     *
     * @mbg.generated
     */
    private BigDecimal price;

    private static final long serialVersionUID = 1L;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getShowsessionid() {
        return showsessionid;
    }

    public void setShowsessionid(Integer showsessionid) {
        this.showsessionid = showsessionid;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", classId=").append(classId);
        sb.append(", showsessionid=").append(showsessionid);
        sb.append(", grade=").append(grade);
        sb.append(", capacity=").append(capacity);
        sb.append(", price=").append(price);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}