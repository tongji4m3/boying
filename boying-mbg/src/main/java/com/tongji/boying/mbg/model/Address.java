package com.tongji.boying.mbg.model;

import java.io.Serializable;

public class Address implements Serializable {
    private Integer addressId;

    private Integer userId;

    private String receiver;

    private String phone;

    /**
     * 省份
     *
     * @mbg.generated
     */
    private String province;

    /**
     * 城市
     *
     * @mbg.generated
     */
    private String city;

    /**
     * 城区
     *
     * @mbg.generated
     */
    private String region;

    /**
     * 街道
     *
     * @mbg.generated
     */
    private String street;

    /**
     * 街道后的详细地址
     *
     * @mbg.generated
     */
    private String details;

    /**
     * 是否是默认地址,bool类型
     *
     * @mbg.generated
     */
    private Boolean defaultAddress;

    private static final long serialVersionUID = 1L;

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Boolean getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(Boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", addressId=").append(addressId);
        sb.append(", userId=").append(userId);
        sb.append(", receiver=").append(receiver);
        sb.append(", phone=").append(phone);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", region=").append(region);
        sb.append(", street=").append(street);
        sb.append(", details=").append(details);
        sb.append(", defaultAddress=").append(defaultAddress);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}