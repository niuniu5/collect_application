package com.niu.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "acl_fill")
public class FillEntity implements Serializable{

    /**志愿ID**/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**学生ID**/
    @Column(length = 16)
    private Long studentId;

    /**志愿院校ID**/
    @Column(length = 16)
    private Integer collegeId;

    /**志愿专业ID**/
    @Column(length = 16)
    private Integer majorId;

    /**志愿排序**/
    @Column(length = 16)
    private Integer sort;

    /**招生批次**/
    @Column(length = 16)
    private String batchNumber;
}
