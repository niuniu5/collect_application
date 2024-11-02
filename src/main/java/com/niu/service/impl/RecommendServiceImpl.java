package com.niu.service.impl;

import com.niu.common.RecommendUtil;
import com.niu.entity.RecommendEntity;
import com.niu.entity.ScoreRecordEntity;
import com.niu.model.Recommend;
import com.niu.model.ScoreRecord;
import com.niu.model.Student;
import com.niu.repository.*;
import com.niu.service.CollegeService;
import com.niu.service.MajorService;
import com.niu.service.RecommendService;
import com.niu.service.StudentService;
import com.niu.vo.RecommendDTO;
import com.niu.vo.RecommendMajorVO;
import com.niu.vo.RecommendVO;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecommendServiceImpl implements RecommendService {

    @Resource
    private RecommendRepository recommendRepository;


    @Resource
    private StudentService studentService;


    @Resource
    private CollegeService collegeService;


    @Resource
    private MajorService majorService;

    @Autowired
    private RecommendUtil recommendUtil;
    @Autowired
    private ScoreRecordRepository scoreRecordRepository;


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
    public List<RecommendVO> findByStudentId(Long studentId) {
        List<RecommendEntity> recommendEntity = recommendRepository.findByStudentId(studentId);
        return convertToRecommendVO(recommendEntity);
    }

    @Override
    public List<RecommendVO> recommend(Long userId, Integer score, Integer rank, Integer provinceId, String subject) {
        //获取缓存数据
        Student studentById = studentService.getStudentById(userId);
        if (IsEqualRecommend(studentById, score, rank, provinceId, subject)) {
            List<RecommendVO> byStudentId = findByStudentId(userId);
            if (!CollectionUtils.isEmpty(byStudentId)) {
                return byStudentId;
            }
        }
        // 从python获取数据
        List<Integer> recommend = recommendUtil.recommend(userId, score, rank, provinceId, subject);

        //通过scoreRecordId 获取院校专业信息
        List<RecommendEntity> recommendEntitys =listRecommendByScoreRecordIDS(recommend,userId);
        // 通过返回直接插入到结果表中
        recommendEntitys.forEach(recommendEntity -> {
            recommendRepository.saveRecommend(userId, recommendEntity.getCollegeId(), recommendEntity.getMajorId(), recommendEntity.getScore());
        });
        //插入 本次推荐结果， 修改student的推荐参数
        Student student = new Student();
        student.setScore(score);
        student.setRank(rank);
        student.setProvinceId(provinceId);
        student.setSubject(subject);
        studentService.updateStudent(student);
        return convertToRecommendVO(recommendEntitys);
    }

    private List<RecommendEntity> listRecommendByScoreRecordIDS(List<Integer> recommend, Long userId) {
        List<RecommendEntity> list = recommend.stream().map(
                x -> {
                    ScoreRecordEntity scoreRecordById = scoreRecordRepository.getScoreRecordById(x);
                    return RecommendEntity.
                            builder().
                            majorId(scoreRecordById.getMajorId()).
                            collegeId(scoreRecordById.getCollegeId()).
                            score(scoreRecordById.getLowScores()).
                            rank(scoreRecordById.getHighRank()).
                            studentId(userId).
                            build();
                }
        ).toList();

        //TODO :test
        list.sort(Comparator.comparing(RecommendEntity::getScore));
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setRank(i);
        }
        return list;
    }


    private boolean IsEqualRecommend(Student studentById, Integer score, Integer rank, Integer provinceId, String subject) {
        if (subject != null && !subject.equals(studentById.getSubject())) {
            return false;
        }
        if (!score.equals(studentById.getScore())) {
            return false;
        }

        if (!rank.equals(studentById.getRank())) {
            return false;
        }

        if (!provinceId.equals(studentById.getProvinceId())) {
            return false;
        }
        return true;


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


    private List<RecommendVO> convertToRecommendVO(List<RecommendEntity> recommendEntity) {
        //赋值 聚合
        Map<Integer, List<RecommendDTO>> groupByCollege = recommendEntity.stream()
                .map(entity -> {
                    Recommend recommend = Recommend.builder()
                            .id(entity.getId())
                            .studentId(entity.getStudentId())
                            .collegeId(entity.getCollegeId())
                            .majorId(entity.getMajorId())
                            .score(entity.getScore())
                            .rank(entity.getRank())
                            .isFill(entity.getIsFill())
                            .build();
                    return RecommendDTO.builder().recommend(recommend).build();
                }).
                map(dto -> {
                    dto.setCollege(collegeService.getCollegeById(dto.getRecommend().getCollegeId()));
                    dto.setMajor(majorService.getMajorById(dto.getRecommend().getMajorId()));
                    dto.setScoreRecords(collegeService.getScoreRecordByCollegeIdAndMajorId(dto.getCollege().getId(), dto.getMajor().getId()));
                    return dto;
                }).collect(Collectors.groupingBy(x -> x.getCollege().getId()));

        //聚合展开 排序
        List<RecommendVO> list = groupByCollege.entrySet().stream().map(entry -> {
            List<RecommendDTO> v = entry.getValue();
            RecommendDTO recommendDTO = v.get(0);
            List<RecommendMajorVO> recommendMajorVOs = v.stream().map(x -> RecommendMajorVO.
                    builder().
                    major(recommendDTO.getMajor()).
                    recommend(x.getRecommend()).
                    scoreRecords(x.getScoreRecords()).
                    build()).toList();

            //降序
            recommendMajorVOs.sort(new Comparator<RecommendMajorVO>() {
                public int compare(RecommendMajorVO a, RecommendMajorVO b) {
                    return a.getRecommend().getScore() - b.getRecommend().getScore();
                }
            });
            return RecommendVO.
                    builder().
                    college(recommendDTO.getCollege()).
                    recommendMajorVOS(recommendMajorVOs).build();

        }).sorted((new Comparator<RecommendVO>() {
            public int compare(RecommendVO a, RecommendVO b) {
                return a.getRecommendMajorVOS().get(0).getRecommend().getScore() -
                        b.getRecommendMajorVOS().get(0).getRecommend().getScore();
            }
        })).toList();
        return list;
    }

}
