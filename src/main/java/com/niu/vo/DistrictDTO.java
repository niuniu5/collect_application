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
public class DistrictDTO {
    @ExcelProperty(index = 0)
    private String provinceName; //
    @ExcelProperty(index = 1)
    private String cityName; //
}
