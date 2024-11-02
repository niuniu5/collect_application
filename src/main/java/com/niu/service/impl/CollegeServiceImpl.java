package com.niu.service.impl;

import com.niu.entity.*;
import com.niu.model.Fill;
import com.niu.model.ScoreRecord;
import com.niu.repository.*;
import com.niu.service.CollegeService;
import com.niu.vo.CollegeCategory;
import com.niu.vo.CollegeNature;
import jakarta.annotation.Resource;
import com.niu.model.College;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CollegeServiceImpl implements CollegeService {

    @Resource
    private CollegeRepository collegeRepository;

    @Resource
    private MajorRepository majorRepository;

    @Resource
    private ScoreRecordRepository scoreRecordRepository;

    @Resource
    private FillRepository fillRepository;

    @Resource
    private DistrictRepository districtRepository;

    @Override
    public Page<CollegeEntity> getCollegeByCondition(Integer provinceId, String collegeName, CollegeCategory category, CollegeNature nature, Pageable pageable) {
        return collegeRepository.getCollegesByCondition(provinceId, collegeName, category, nature, pageable);
    }

    @Override
    public void updateCollegeProvinceId(Integer provinceId, Integer id) {
        collegeRepository.updateCollegeProvinceIdById(provinceId, id);
    }

    @Override
    public List<College> getAllColleges() {
        List<CollegeEntity> colleges = collegeRepository.findAll();
        return colleges.stream()
                .map(this::convertToCollege)
                .collect(Collectors.toList());
    }

    @Override
    public College getCollegeById(Integer id) {
        CollegeEntity collegeEntity = collegeRepository.getCollegeById(id);
        return convertToCollege(collegeEntity);
    }

    @Override
    public College getCollegeByName(String name) {
        CollegeEntity collegeEntity = collegeRepository.getCollegeByName(name);
        return convertToCollege(collegeEntity);
    }


    @Override
    public List<College> getCollegeByNameLike(String name) {
        String pattern = "%" + name + "%";
        List<CollegeEntity> collegeEntities = collegeRepository.getCollegeByNameLike(pattern);
        return collegeEntities.stream()
                .map(this::convertToCollege)
                .collect(Collectors.toList());
    }

    @Override
    public Page<CollegeEntity> getCollegesByProvinceId(Integer provinceId, Pageable pageable) {
        Page<CollegeEntity> colleges = collegeRepository.getCollegesByProvinceId(provinceId, pageable);
        return colleges;

//                .map(this::convertToCollege)
//                .collect(Collectors.toList());
    }


    /**
     * @param provinceId
     * @param category
     * @param pageable
     * @return
     */
    @Override
    public Page<CollegeEntity> getCollegesByProvinceIdAndCategory(Integer provinceId, CollegeCategory category, Pageable pageable) {
        return collegeRepository.getCollegesByProvinceIdAndCategory(provinceId, category, pageable);
    }

    /**
     * @param provinceId
     * @param category
     * @param nature
     * @param pageable
     * @return
     */
    @Override
    public Page<CollegeEntity> getCollegesByProvinceIdAndCategoryAndNature(Integer provinceId, CollegeCategory category, CollegeNature nature, Pageable pageable) {
        return collegeRepository.getCollegesByProvinceIdAndCategoryAndNature(provinceId, category, nature, pageable);
    }

    /**
     * @param pageable
     * @param id
     * @return
     */
    @Override
    public Page<MajorEntity> getMajorByCollegeId(Integer id, Pageable pageable) {
        return majorRepository.getMajorByCollegeId(id, pageable);
    }

    @Override
    public List<ScoreRecord> getScoreRecordByCollegeId(Integer collegeId) {
        List<ScoreRecordEntity> scoreRecordEntities = scoreRecordRepository.getScoreRecordByCollegeId(collegeId);
        return scoreRecordEntities.stream()
                .map(this::convertToScoreRecord)
                .collect(Collectors.toList());
    }


    @Override
    public List<ScoreRecord> getScoreRecordByCollegeIdAndMajorId(Integer collegeId, Integer majorId) {
        List<ScoreRecordEntity> scoreRecordEntities = scoreRecordRepository.getScoreRecordByCollegeIdAndMajorId(collegeId,majorId);
        return scoreRecordEntities.stream()
                .map(this::convertToScoreRecord)
                .collect(Collectors.toList());
    }


    private ScoreRecord convertToScoreRecord(ScoreRecordEntity scoreRecordEntity) {
        return new ScoreRecord(
                scoreRecordEntity.getId(),
                scoreRecordEntity.getProvinceId(),
                scoreRecordEntity.getYear(),
                scoreRecordEntity.getCollegeId(),
                scoreRecordEntity.getMajorId(),
                scoreRecordEntity.getBatchNumber(),
                scoreRecordEntity.getType(),
                scoreRecordEntity.getNumberof(),
                scoreRecordEntity.getHighScores(),
                scoreRecordEntity.getHighRank(),
                scoreRecordEntity.getLowScores(),
                scoreRecordEntity.getLowRank(),
                scoreRecordEntity.getAvgScores(),
                scoreRecordEntity.getAvgRank()
        );
    }


    private College convertToCollege(CollegeEntity collegeEntity) {
        return new College(collegeEntity.getId(), collegeEntity.getName(), collegeEntity.getFormerName(),
                collegeEntity.getInfo(), collegeEntity.getProvinceId(), collegeEntity.getCityId(),
                collegeEntity.getSorted(), collegeEntity.getIs985(), collegeEntity.getIs211(), collegeEntity.getIsDoubleFirstClass(),
                collegeEntity.getAttribution(), collegeEntity.getCategory(), collegeEntity.getNature(), collegeEntity.getEnrollmentCode());
    }

    @Override
    public List<Fill> getFillByStudentId(Long studentId) {
        List<FillEntity> fillEntity = fillRepository.findByStudentId(studentId);
        return fillRepository.findByStudentId(studentId).stream()
                .map(this::convertToFill)
                .collect(Collectors.toList());
    }

    @Override
    public void saveFill(FillEntity fillEntity) {
        fillRepository.save(fillEntity);
    }

    @Override
    public FillEntity addUserVolunteer(FillEntity userVolunteer) {
        return null;
    }

    private Fill convertToFill(FillEntity fillEntity) {
        return new Fill(fillEntity.getId(), fillEntity.getStudentId(), fillEntity.getCollegeId(), fillEntity.getMajorId(), fillEntity.getSort(), fillEntity.getBatchNumber());
    }

    @Override
    public List<DistrictEntity> getAllDistrict() {
        return districtRepository.findAll();
    }

    @Override
    public Integer getProvinceIdByCityId(int cityId) {
        return districtRepository.getProvinceIdByCityId(cityId);
    }

}
