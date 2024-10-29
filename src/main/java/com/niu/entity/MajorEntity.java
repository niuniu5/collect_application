package com.niu.entity;

import com.niu.vo.AcademicType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "acl_major")
public class MajorEntity implements Serializable {


    /**ID：自增长**/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**专业名称**/
    @Column(length = 128)
    private String name;

    /**所属大学**/
    @Column(name = "college_id")
    private Integer collegeId;

    /**科类**/
    @Enumerated(EnumType.STRING)
    private AcademicType type;

    /**一级门类**/
    @Column(length = 32)
    private String subject;

    /**招生代码**/
    @Column(length = 10)
    private String code;

    /**是否国家特色专业**/
    private Boolean characteristic;

    /**专业条件组合, 比如 物理+不限, 物理+(化或生)**/
    private String subjectGroup;

}
