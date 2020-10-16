package com.tongji.boying.mbg.model;

import java.io.Serializable;

public class Category implements Serializable {
    private Integer categoryId;

    /**
     * 上级分类的编号：0表示一级分类
     *
     * @mbg.generated
     */
    private Integer parentId;

    private String name;

    /**
     * 分类级别
     *
     * @mbg.generated
     */
    private Integer level;

    /**
     * 用于排序,0则不显示
     *
     * @mbg.generated
     */
    private Integer weight;

    /**
     * 该目录的图标
     *
     * @mbg.generated
     */
    private String icon;

    private String description;

    private static final long serialVersionUID = 1L;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
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
        sb.append(", categoryId=").append(categoryId);
        sb.append(", parentId=").append(parentId);
        sb.append(", name=").append(name);
        sb.append(", level=").append(level);
        sb.append(", weight=").append(weight);
        sb.append(", icon=").append(icon);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}