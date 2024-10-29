package com.niu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 智能推荐大学和专业结果
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "acl_recommend")
public class RecommendEntity implements Serializable {

    @Id
    private Long id;

    /**学生ID*/
    @Column(name = "student_id")
    private Integer studentId;

    /**推荐大学*/
    @Column(name = "college_id")
    private Integer collegeId;

    /**推荐专业*/
    @Column(name = "major_id")
    private Integer majorId;

    /**推荐得分：得分越高越保险*/
    private Integer score;

    /**志愿排名*/
    private Integer rank;

    /**是否填报*/
    @Column(name = "is_fill")
    private Boolean isFill;

}
