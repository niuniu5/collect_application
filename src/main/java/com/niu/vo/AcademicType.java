package com.niu.vo;

/**
 * 科类：文科，理科
 */
public enum AcademicType {
    SCIENCE("理科"),
    ARTS("文科"),
    OTHER("其它");

    private final String description;
    AcademicType(String description) {
        this.description = description;
    }
    public String getName() {
        return name();
    }
    public String getDescription() {
        return description;
    }
    public static AcademicType fromDescription(String description) {
        for (AcademicType type : values()) {
            if (type.description.equals(description)) {
                return type;
            }
        }
        return null;
    }
}
