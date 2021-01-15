package com.tongji.boying.model;

public class BoyingCategory {
    private Long id;

    private String name;

    private String icon;

    private String description;

    private Long weight;

    private String adminDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public String getAdminDelete() {
        return adminDelete;
    }

    public void setAdminDelete(String adminDelete) {
        this.adminDelete = adminDelete == null ? null : adminDelete.trim();
    }
}