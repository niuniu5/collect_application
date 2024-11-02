package com.niu.model;

import com.niu.vo.AcademicType;
import lombok.*;

import java.io.Serializable;

/**
 * 大学专业
 * @author 牛亚平
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Major implements Serializable {

    /**ID：自增长**/
    private Integer id;

    /**专业名称**/
    private String name;

    /**招生代码**/
    private String code;

    /**是否是特色科目**/
    private Boolean characteristic;

    /**选课要求**/
    private String subjectGroup;

    /**历年最低分**/
    private int lowScore;

    /**历年最低位次**/
    private int lowRank;

    /**招生人数**/
    private int numberof;
}
