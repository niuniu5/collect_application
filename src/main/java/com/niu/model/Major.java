package com.niu.model;

import com.niu.vo.AcademicType;
import lombok.Data;

import java.io.Serializable;

/**
 * 大学专业
 * @author 牛亚平
 */
@Data
public class Major implements Serializable {

    /**ID：自增长**/
    private Integer id;

    /**专业名称**/
    private String name;

    /**科类**/
    private AcademicType type;

    /**一级门类**/
    private String subject;

    /**所属大学**/
    private College college;

    /**招生代码**/
    private String code;

    /**是否国家特色专业**/
    private Boolean characteristic;

}
