package com.niu.repository;
import com.niu.entity.RecommendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RecommendRepository extends JpaRepository<RecommendEntity, Long> {

    /**
     * 保存推荐记录
     * @param studentId
     * @param collegeId
     * @param majorId
     * @param score
     */
    @Transactional
    @Modifying
    @Query(value = "insert into acl_recommend(student_id, college_id, major_id, score) values(?1, ?2, ?3, ?4)", nativeQuery = true)
    void saveRecommend(Long studentId, Integer collegeId, Integer majorId, Integer score);

    /**
     * 根据学生id查询推荐记录
     * @param studentId
     * @return
     */
    List<RecommendEntity> findByStudentId(Long studentId);

    /**
     * 更新填报记录,根据ID,更新填报状态和 志愿排名
     */
    @Transactional
    @Modifying
    @Query(value = "update acl_recommend set is_fill = ?1, rank = ?2 where id = ?3", nativeQuery = true)
    void updateFill(Boolean isFill, Integer rank, Long id);


    /**
     * 更新志愿排序
     */
    @Transactional
    @Modifying
    @Query(value = "update acl_recommend set rank = ?1 where id = ?2", nativeQuery = true)
    void updateRank(Integer rank, Long id);

    /**
     * 删除志愿记录,更新填报状态
     */
    @Transactional
    @Modifying
    @Query(value = "update acl_recommend set is_fill = false where id = ?1", nativeQuery = true)
    void deleteRecommend(Long id);

}
