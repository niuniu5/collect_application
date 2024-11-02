package com.niu.repository;

import com.niu.entity.ScoreRecordEntity;
import com.niu.model.ScoreRecord;
import com.niu.vo.EnrollmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRecordRepository extends JpaRepository<ScoreRecordEntity, Long> {

    /** 获取录取分数线记录, 根据大学ID、专业ID、批次号、录取类型 */
    ScoreRecordEntity getScoreRecordByCollegeIdAndMajorIdAndBatchNumberAndType(Integer collegeId, Integer majorId, String batchNumber, EnrollmentType type);

    /** 获取录取分数线记录, 根据大学ID */
    List<ScoreRecordEntity> getScoreRecordByCollegeId(Integer collegeId);


    /** 通过ID获取 */
    ScoreRecordEntity  getScoreRecordById(Integer id);

    /** 获取录取分数线记录, 根据大学ID和专业ID */
    List<ScoreRecordEntity> getScoreRecordByCollegeIdAndMajorId(Integer collegeId, Integer majorId);
}
