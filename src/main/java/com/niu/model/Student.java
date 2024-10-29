package com.niu.model;

import com.niu.vo.AcademicType;
import com.niu.vo.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 考生信息
 * @author 牛亚平
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Student implements Serializable {

    /**雪花ID*/
    private Long id;

    /**考生姓名**/
    private String name;

    /**电话**/
    private String phone;

    /**性别**/
    private Gender gender;

    /**出生日期**/
    private Date birthday;

    /**中学所在省**/
    private Integer provinceId;

    /**中学所在市**/
    private Integer cityId;

    /**中学所在县**/
    private Integer areaId;

    /**中学名称**/
    private String school;

    /**考生兴趣**/
    private String interests;

    /**考生成绩**/
    private Integer score;

    /**考生文理科方向**/
    private AcademicType academicType;

    /**考生加分项**/
    private Integer pluses;

    /**考生科目**/
    private String subject;

    /**考生排名**/
    private Integer rank;
}
