package com.niu.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** 定义一个DTO类来表示Excel文件中的每一行数据，
 * 并使用@ExcelProperty注解来指定列的索引或名称： */

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CollegeDTO {
    @ExcelProperty(index = 1)
    private String collegeName; //大学名称
    @ExcelProperty(index = 2)
    private String provinceName; // 省份名称
    @ExcelProperty(index = 3)
    private String cityName; //市
    @ExcelProperty(index = 4)
    private Integer sort; //软科排名
    @ExcelProperty(index = 5)
    private String is985; //是否985
    @ExcelProperty(index = 6)
    private String is211; //是否211
    @ExcelProperty(index = 7)
    private String isdouble; //是否双一流
    @ExcelProperty(index = 17) //  办学性质
    private String natureName; // 1. 公办,民办
    @ExcelProperty(index = 18) //学校归属
    private String collegeBelong;
    @ExcelProperty(index = 19) //全国统一招生代码
    private String code;
    @ExcelProperty(index = 21) // 招生类型
    private String type; // 1. 综合, 理工,农林...
    @ExcelProperty(index = 23) // 学校曾用名
    private String oldName;
}
