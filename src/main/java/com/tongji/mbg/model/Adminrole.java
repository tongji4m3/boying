package com.tongji.mbg.model;

import java.io.Serializable;

public class Adminrole implements Serializable {
    private Integer adminroleid;

    private Integer adminid;

    private Integer roleid;

    private static final long serialVersionUID = 1L;

    public Integer getAdminroleid() {
        return adminroleid;
    }

    public void setAdminroleid(Integer adminroleid) {
        this.adminroleid = adminroleid;
    }

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", adminroleid=").append(adminroleid);
        sb.append(", adminid=").append(adminid);
        sb.append(", roleid=").append(roleid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}