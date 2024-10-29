package com.niu.vo;

/**
 * 招生类型：普通类，单招类，定向类
 */
public enum EnrollmentType {
    COMMON("普通类"),
    ARTS("艺术类"),
    SINGLE("单招类"),
    ;

    private final String description;
    EnrollmentType(String description) {
        this.description = description;
    }
    public String getName() {
        return name();
    }
    public String getDescription() {
        return description;
    }

    public static EnrollmentType fromDescription(String description) {
        for (EnrollmentType type : EnrollmentType.values()) {
            if (type.description.equals(description)) {
                return type;
            }
        }
        return null;
    }
}
