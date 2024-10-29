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
public class ScoreRecordDTONew2 {
    @ExcelProperty(index = 0)
    private Integer year; //年份
    @ExcelProperty(index = 1)
    private String collegeCode;//学校代码
    @ExcelProperty(index = 2)
    private String collegeName;//学校名称
    @ExcelProperty(index = 3)
    private String batchNumber;// 批次,本科一批,二批
    @ExcelProperty(index = 4)
    private String subject;// 选科目,如物理+不限, 物理+(化学或生物)
    @ExcelProperty(index = 5)
    private String majorCode; // 专业代码
    @ExcelProperty(index = 6)
    private String majorName;//专业名称
    @ExcelProperty(index = 7)
    private String other; //备注
    @ExcelProperty(index = 8) // 最低分排名
    private String lowRank;
    @ExcelProperty(index = 9) // 最低分
    private String lowScore;

    @ExcelProperty(index = 11) // 平均分
    private String avgScore;
    @ExcelProperty(index = 13) // 最高分
    private String highScore;
    @ExcelProperty(index = 14) //招生数
    private String count;

}
