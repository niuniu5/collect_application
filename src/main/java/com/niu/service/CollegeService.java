package com.niu.service;

import com.niu.entity.CollegeEntity;
import com.niu.entity.DistrictEntity;
import com.niu.entity.FillEntity;
import com.niu.entity.MajorEntity;
import com.niu.model.College;
import com.niu.model.Fill;
import com.niu.model.ScoreRecord;
import com.niu.vo.CollegeCategory;
import com.niu.vo.CollegeNature;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CollegeService {

    Page<CollegeEntity> getCollegeByCondition(Integer provinceId, String collegeName, CollegeCategory category,
                                              CollegeNature nature,Boolean is985, Boolean is211,
                                              Boolean isDoubleFirstClass,String attribution, Pageable pageable);
    List<DistrictEntity> getAllDistrict();
    Integer getProvinceIdByCityId(int cityId);

    void updateCollegeProvinceId(Integer provinceId, Integer id);

    List<College> getAllColleges();
    College getCollegeById(Integer id);
    List<College> getCollegeByNameLike(String name);
    College getCollegeByName(String name);
    Page<CollegeEntity>  getCollegesByProvinceId(Integer provinceId, Pageable pageable);
    Page<CollegeEntity> getCollegesByProvinceIdAndCategory(Integer provinceId, CollegeCategory category, Pageable pageable);
    Page<CollegeEntity> getCollegesByProvinceIdAndCategoryAndNature(Integer provinceId, CollegeCategory category, CollegeNature collegeNature, Pageable pageable);
    Page<MajorEntity>  getMajorByCollegeId(Integer id, Pageable pageable);
    List<ScoreRecord> getScoreRecordByCollegeId(Integer collegeId);
    List<Fill> getFillByStudentId(Long studentId);
    void saveFill(FillEntity fillEntity);

    FillEntity addUserVolunteer(FillEntity userVolunteer);


}
