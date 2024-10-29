package com.niu.model;

import com.niu.vo.CollegeCategory;
import com.niu.vo.CollegeNature;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class College implements Serializable {

    /**ID：字增长**/
    private Integer id;

    /**大学名称**/
    private String name;

    /**曾用名**/
    private String formerName;

    /**大学简介**/
    private String info;

    /**所属省**/
    private Integer provinceId;

    /**所属市**/
    private Integer cityId;

    /**软科排名**/
    private Integer sorted;

    /**是否985**/
    private Boolean is985;

    /**是否211**/
    private Boolean is211;

    /**是否双一流**/
    private Boolean isDoubleFirstClass;

    /**大学归属：如教育部，中央部委**/
    private String attribution;

    /**学校类别**/
    private CollegeCategory category;

    /**办学性质**/
    private CollegeNature nature;

    /**招生代码**/
    private String enrollmentCode;

}
