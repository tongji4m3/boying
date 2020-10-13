package com.tongji.mbg.model;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable {
    private Integer roleid;

    /**
     * 角色名称
     *
     * @mbg.generated
     */
    private String name;

    /**
     * 角色描述
     *
     * @mbg.generated
     */
    private String description;

    /**
     * 后台用户数量
     *
     * @mbg.generated
     */
    private Integer adminCount;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * 启用状态：0->禁用；1->启用
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     * 用于排序
     *
     * @mbg.generated
     */
    private Integer sort;

    private static final long serialVersionUID = 1L;

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAdminCount() {
        return adminCount;
    }

    public void setAdminCount(Integer adminCount) {
        this.adminCount = adminCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", roleid=").append(roleid);
        sb.append(", name=").append(name);
        sb.append(", description=").append(description);
        sb.append(", adminCount=").append(adminCount);
        sb.append(", createTime=").append(createTime);
        sb.append(", status=").append(status);
        sb.append(", sort=").append(sort);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}