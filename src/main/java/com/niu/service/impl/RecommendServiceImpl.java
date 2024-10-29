package com.niu.service.impl;

import com.niu.entity.RecommendEntity;
import com.niu.model.Recommend;
import com.niu.repository.RecommendRepository;
import com.niu.service.RecommendService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RecommendServiceImpl implements RecommendService {

    @Resource
    private RecommendRepository recommendRepository;

    @Override
    public void saveRecommend(Long studentId, Integer collegeId, Integer majorId, Integer score) {
        recommendRepository.saveRecommend(studentId, collegeId, majorId, score);
    }

    @Override
    public void updateFill(Boolean isFill, Integer rank, Long id) {
        recommendRepository.updateFill(isFill, rank, id);
    }

    @Override
    public void updateRank(Integer rank, Long id) {
        recommendRepository.updateRank(rank, id);
    }

    @Override
    public void deleteRecommend(Long id) {
        recommendRepository.deleteRecommend(id);
    }

    @Override
    public List<Recommend> findByStudentId(Long studentId) {
        List<RecommendEntity> recommendEntity = recommendRepository.findByStudentId(studentId);
        return convertToRecommend(recommendEntity);
    }

    private List<Recommend> convertToRecommend(List<RecommendEntity> recommendEntity) {
        return (List<Recommend>) recommendEntity.stream()
                .map(entity -> Recommend.builder()
                        .id(entity.getId())
                        .studentId(entity.getStudentId())
                        .collegeId(entity.getCollegeId())
                        .majorId(entity.getMajorId())
                        .score(entity.getScore())
                        .rank(entity.getRank())
                        .isFill(entity.getIsFill())
                        .build());
    }
}
