package com.niu.vo;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** 定义一个DTO类来表示Excel文件中的每一行数据，并使用@ExcelProperty注解来指定列的索引或名称： */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MajorDTO {
    @ExcelProperty(index = 1)
    private String collegeName; //大学名称
    @ExcelProperty(index = 10)
    private String nature; //门类
    @ExcelProperty(index = 11)
    private String subject; //一级学科
    @ExcelProperty(index = 12)
    private String majorName; //专业名称
    @ExcelProperty(index = 8)
    private String category; //文理科
}
