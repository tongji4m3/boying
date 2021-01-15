package com.tongji.boying.model;

public class BoyingCategory {
    private Integer id;

    private String name;

    private String icon;

    private String description;

    private Integer weight;

    private Short adminDelete;

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
        this.name = name == null ? null : name.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Short getAdminDelete() {
        return adminDelete;
    }

    public void setAdminDelete(Short adminDelete) {
        this.adminDelete = adminDelete;
    }
}