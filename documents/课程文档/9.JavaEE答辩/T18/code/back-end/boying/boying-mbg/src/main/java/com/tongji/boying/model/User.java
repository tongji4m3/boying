package com.tongji.boying.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer userId;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "联系电话")
    private String phone;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "用户真实姓名")
    private String realName;
    @ApiModelProperty(value = "用户身份证号")
    private String identityNumber;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "年龄")
    private Integer age;
    @ApiModelProperty(value = "用户性别 1->男,0->女")
    private Boolean gender;
    @ApiModelProperty(value = "帐号启用状态：0->禁用；1->启用")
    private Boolean userstatus;
    @ApiModelProperty(value = "个人头像")
    private String icon;
    @ApiModelProperty(value = "默认观影者")
    private Integer defaultFrequent;
    @ApiModelProperty(value = "默认地址")
    private Integer defaultAddress;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Boolean getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(Boolean userstatus) {
        this.userstatus = userstatus;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getDefaultFrequent() {
        return defaultFrequent;
    }

    public void setDefaultFrequent(Integer defaultFrequent) {
        this.defaultFrequent = defaultFrequent;
    }

    public Integer getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(Integer defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", username=").append(username);
        sb.append(", phone=").append(phone);
        sb.append(", password=").append(password);
        sb.append(", realName=").append(realName);
        sb.append(", identityNumber=").append(identityNumber);
        sb.append(", email=").append(email);
        sb.append(", age=").append(age);
        sb.append(", gender=").append(gender);
        sb.append(", userstatus=").append(userstatus);
        sb.append(", icon=").append(icon);
        sb.append(", defaultFrequent=").append(defaultFrequent);
        sb.append(", defaultAddress=").append(defaultAddress);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
