package com.claykab.photoApp.model;

public class CategoryType {
    private String category;
    private String categoryImageUrl;

    public CategoryType(String category, String categoryImageUrl) {
        this.category = category;
        this.categoryImageUrl = categoryImageUrl;
    }

    public String getCategory() {
        return category;
    }

    public CategoryType setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getCategoryImageUrl() {
        return categoryImageUrl;
    }

    public CategoryType setCategoryImageUrl(String categoryImageUrl) {
        this.categoryImageUrl = categoryImageUrl;
        return this;
    }
}
