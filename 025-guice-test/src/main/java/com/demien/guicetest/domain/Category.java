package com.demien.guicetest.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {

    @Id
    @Column
    private Long categoryId;

    @Column
    private String categoryName;

    @Column
    private Long parentCateoryId;

    public Category() {}

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getParentCateoryId() {
        return parentCateoryId;
    }

    public void setParentCateoryId(Long parentCateoryId) {
        this.parentCateoryId = parentCateoryId;
    }
}
