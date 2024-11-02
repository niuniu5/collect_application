package com.niu.vo;

import com.niu.model.College;
import com.niu.model.Recommend;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RecommendVO {


    //通过推荐信息查询出来的院校信息
    private College college;

    //通过推荐信息查询出来的专业信息
    private List<RecommendMajorVO> recommendMajorVOS;



}
