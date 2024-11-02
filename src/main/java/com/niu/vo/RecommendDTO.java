package com.niu.vo;

import com.niu.model.College;
import com.niu.model.Major;
import com.niu.model.Recommend;
import com.niu.model.ScoreRecord;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RecommendDTO {

    //推荐信息
    private Recommend recommend;

    //通过推荐信息查询出来的专业信息
    private Major major;

    //通过 院校 跟专业查询出来的历年填报信息
    private List<ScoreRecord> scoreRecords;

    //通过推荐信息查询出来的院校信息
    private College college;


}
