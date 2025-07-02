package com.example.hello.entity;

import java.time.LocalDateTime;

/**
 * 公司实体类
 */
public class Company {
    private Integer id;
    private String name;
    private String website;
    private String logoUrl;
    private String financingStage;
    private String description;
    private String introVideoUrl;
    private Boolean wantsVideoSupport;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // 构造函数
    public Company() {}

    // Getter和Setter方法
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getFinancingStage() {
        return financingStage;
    }

    public void setFinancingStage(String financingStage) {
        this.financingStage = financingStage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIntroVideoUrl() {
        return introVideoUrl;
    }

    public void setIntroVideoUrl(String introVideoUrl) {
        this.introVideoUrl = introVideoUrl;
    }

    public Boolean getWantsVideoSupport() {
        return wantsVideoSupport;
    }

    public void setWantsVideoSupport(Boolean wantsVideoSupport) {
        this.wantsVideoSupport = wantsVideoSupport;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", website='" + website + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", financingStage='" + financingStage + '\'' +
                ", description='" + description + '\'' +
                ", introVideoUrl='" + introVideoUrl + '\'' +
                ", wantsVideoSupport=" + wantsVideoSupport +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
} 