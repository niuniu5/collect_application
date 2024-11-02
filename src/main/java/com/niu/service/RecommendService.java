package com.niu.service;

import com.niu.entity.RecommendEntity;
import com.niu.model.Recommend;
import com.niu.vo.RecommendVO;

import java.util.List;

public interface RecommendService {
    void saveRecommend(Long studentId, Integer collegeId, Integer majorId, Integer score);
    void updateFill(Boolean isFill, Integer rank, Long id);
    void updateRank(Integer rank, Long id);
    void deleteRecommend(Long id);

    List<RecommendVO>  findByStudentId(Long studentId);
    List<RecommendVO> recommend(Long userId, Integer score, Integer rank, Integer provinceId, String subject);
}
