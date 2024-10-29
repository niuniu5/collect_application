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
public class ScoreRecordDTO {
    @ExcelProperty(index = 0)
    private Integer year;
    @ExcelProperty(index = 1)
    private String collegeName;
    @ExcelProperty(index = 2)
    private String provinceName; // 省份名称
    @ExcelProperty(index = 12) //专业名称
    private String majorName;
    @ExcelProperty(index = 9) // 批次,本科一批,二批
    private String batchNumber;
    @ExcelProperty(index = 21) // 招生类型
    private String type; // 1. 综合, 理工,农林...
    @ExcelProperty(index = 15) // 最低分
    private String lowScore;
    @ExcelProperty(index = 16) // 最低分排名
    private String lowRank;
    @ExcelProperty(index = 13) // 平均分
    private String avgScore;
    @ExcelProperty(index = 14) // 最高分
    private String highScore;
//    @ExcelProperty(index = 3)
//    private String cityName; //市
//    @ExcelProperty(index = 4)
//    private Integer sort; //软科排名
//    @ExcelProperty(index = 5)
//    private String is985; //是否985
//    @ExcelProperty(index = 6)
//    private String is211; //是否211
//    @ExcelProperty(index = 7)
//    private String isdouble; //是否双一流
    @ExcelProperty(index = 8)
    private String category; //文理科
//
//    @ExcelProperty(index = 10) //门类
//    private String nature;
    @ExcelProperty(index = 11) //一级学科
    private String subject;
//
//    @ExcelProperty(index = 13) // 平均分
//    private Integer avgScore;

//    @ExcelProperty(index = 15) // 最低分
//    private Integer lowScore;
//    @ExcelProperty(index = 16) // 最低分排名
//    private Integer lowRank;
//    @ExcelProperty(index = 17) //  办学性质
//    private String natureName; // 1. 公办,民办
//    @ExcelProperty(index = 18) //学校归属
//    private String collegeBelong;
//    @ExcelProperty(index = 19) //全国统一招生代码
//    private String code;
//
//    @ExcelProperty(index = 22) //学历类别
//    private String education; // 普通本科, 专科（高职）
//    @ExcelProperty(index = 23) // 学校曾用名
//    private String oldName;
//    @ExcelProperty(index = 23) //生源地
//    private String origin;

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

    @ExcelProperty(index = 23) // 学校曾用名
    private String oldName;
}
