package com.tongji.boying.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class BoyingCategory implements Serializable {
    private Integer id;

    @ApiModelProperty(value = "目录名称")
    private String name;

    @ApiModelProperty(value = "用于排序,0则不显示")
    private Integer weight;

    @ApiModelProperty(value = "管理员是否删除了该目录,默认为0，即未删除状态")
    private Boolean adminDelete;

    @ApiModelProperty(value = "该目录的图标")
    private String icon;

    @ApiModelProperty(value = "目录描述")
    private String description;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Boolean getAdminDelete() {
        return adminDelete;
    }

    public void setAdminDelete(Boolean adminDelete) {
        this.adminDelete = adminDelete;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", weight=").append(weight);
        sb.append(", adminDelete=").append(adminDelete);
        sb.append(", icon=").append(icon);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}