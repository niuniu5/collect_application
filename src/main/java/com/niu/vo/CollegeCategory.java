package com.niu.vo;
import lombok.Getter;
/**大学类别：综合类,理工类,农林类,财经类,民族类,军事类,师范类,体育类,医药类,语言类,艺术类,政法类,其他**/
@Getter
public enum CollegeCategory {
    COMPREHENSIVE("综合类"),
    SCIENCE("理工类"),
    AGRICULTURE("农林类"),
    FINANCE("财经类"),
    NATIONALITY("民族类"),
    MILITARY("军事类"),
    TEACHERS("师范类"),
    SPORTS("体育类"),
    MEDICINE("医药类"),
    LANGUAGE("语言类"),
    ART("艺术类"),
    LAW("政法类"),
    OTHER("其他");
    private final String description;
    CollegeCategory(String description) {
        this.description = description;
    }
    public String getName() {
        return name();
    }
    // 反向查找枚举值的方法
    public static CollegeCategory fromDescription(String description) {
        for (CollegeCategory category : values()) {
            if (category.getDescription().equals(description) || category.getDescription().contains(description)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invlid description: " + description);
    }
}
