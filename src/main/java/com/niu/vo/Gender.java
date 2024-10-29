package com.niu.vo;

public enum Gender {
    FEMALE("女生"), //0
    MALE("男生");   //1

    private final String description;
    Gender(String description) {
        this.description = description;
    }
    public String getName() {
        return name();
    }
    public String getDescription() {
        return description;
    }

}
