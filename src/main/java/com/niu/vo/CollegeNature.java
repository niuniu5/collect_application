package com.niu.vo;

/**
 * 办学性质
 */
public enum CollegeNature {
    PUBLIC("公办"),
    PRIVATE("民办"),
    COOPERATE("中外合作办学"),
    COOPERATIVE("内地与港澳台地区合作办学")
    ;

    private final String description;
    CollegeNature(String description) {
        this.description = description;
    }
    public String getName() {
        return name();
    }
    public String getDescription() {
        return description;
    }
    // 反向查找枚举值的方法
    public static CollegeNature fromDescription(String description) {
        for (CollegeNature category : values()) {
            if (category.getDescription().equals(description) || category.getDescription().contains("description")) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid description: " + description);
    }
}
