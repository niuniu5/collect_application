package com.niu.model;

import lombok.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Fill implements Serializable{

    /**志愿ID**/
    private Integer id;

    /**学生ID**/
    private Long studentId;

    /**志愿院校ID**/
    private Integer collegeId;

    /**志愿专业ID**/
    private Integer majorId;

    /**志愿排序**/
    private Integer sort;

    /**招生批次**/
    private String batchNumber;

}
