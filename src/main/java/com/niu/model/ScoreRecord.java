package com.niu.model;

import com.niu.vo.EnrollmentType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 历史录取分数线记录
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ScoreRecord implements Serializable {

    /**自增长ID*/
    private Integer id;

    /**生源地，省ID**/
    private Integer provinceId;

    /**年份**/
    private Integer year;

    /**大学**/
    private Integer collegeId;

    /**大学专业**/
    private Integer majorId;

    /**批次：一本，二本，专科**/
    private String batchNumber;

    /**招生类型**/
    private EnrollmentType type;

    /**招生人数**/
    private Integer numberof;

    /**最高分**/
    private Integer highScores;

    /**最高排次**/
    private Integer highRank;

    /**最低分**/
    private Integer lowScores;

    /**最低排次**/
    private Integer lowRank;

    /**平均分**/
    private Integer avgScores;

    /**平均排次**/
    private Integer  avgRank;

}
