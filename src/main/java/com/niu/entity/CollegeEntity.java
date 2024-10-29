package com.niu.entity;

import com.niu.vo.CollegeCategory;
import com.niu.vo.CollegeNature;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "acl_college")
public class CollegeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**自增长ID*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**大学名称**/
    @Column(length = 32)
    private String name;

    /**曾用名**/
    @Column(name = "former_name", length = 64)
    private String formerName;

    /**大学简介**/
    @Column(length = 1024)
    private String info;

    /**所属省**/
    @Column(name = "province_id")
    private Integer provinceId;

    /**所属市**/
    @Column(name = "city_id")
    private Integer cityId;

    /**软科排名**/
    private Integer sorted;

    /**是否985**/
    private Boolean is985;
    /**是否211**/
    private Boolean is211;
    /**是否双一流**/
    @Column(name = "is_double_first_class")
    private Boolean isDoubleFirstClass;

    /**大学归属：如教育部，中央部委**/
    @Column(length = 32)
    private String attribution;

    /**学校类别**/
    @Enumerated(EnumType.STRING)
    private CollegeCategory category;

    /**办学性质**/
    @Enumerated(EnumType.STRING)
    private CollegeNature nature;

    /**招生代码**/
    @Column(name = "enrollment_code", length = 10)
    private String enrollmentCode;

}
