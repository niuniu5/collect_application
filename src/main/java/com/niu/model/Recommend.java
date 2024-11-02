package com.niu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

/**
 * 智能推荐大学和专业结果
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Recommend implements Serializable {


    private Long id;

    /**学生ID*/
    private Long studentId;

    /**推荐大学*/
    private Integer collegeId;

    /**推荐专业*/
    private Integer majorId;

    /**推荐得分：得分越高越保险*/
    private Integer score;

    /**排序**/
    private Integer rank;

    /**是否填报**/
    private Boolean isFill;
}
