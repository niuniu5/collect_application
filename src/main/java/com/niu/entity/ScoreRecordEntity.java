package com.niu.entity;

import com.niu.vo.EnrollmentType;
import jakarta.persistence.*;
import lombok.*;

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
@Entity
@Table(name = "acl_score_record")
public class ScoreRecordEntity implements Serializable {

    /**自增长ID*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**生源地，省ID**/
    @Column(name = "province_id")
    private Integer provinceId;

    /**年份**/
    @Column(name = "year")
    private Integer year;

    /**大学**/
    @Column(name = "college_id")
    private Integer collegeId;

    /**大学专业**/
    @Column(name = "major_id")
    private Integer majorId;

    /**批次：一本，二本，专科**/
    @Column(name = "batch_number", length = 10)
    private String batchNumber;

    /**招生类型**/
    @Enumerated(EnumType.STRING)
    private EnrollmentType type;

    /**招生人数**/
    private Integer numberof;

    /**最高分**/
    @Column(name = "high_scores")
    private Integer highScores;

    /**最高排次**/
    @Column(name = "high_rank")
    private Integer highRank;

    /**最低分**/
    @Column(name = "low_scores")
    private Integer lowScores;

    /**最低排次**/
    @Column(name = "low_rank")
    private Integer lowRank;

    /**平均分**/
    @Column(name = "avg_scores")
    private Integer avgScores;

    /**平均排次**/
    @Column(name = "avg_rank")
    private Integer avgRank;

}
