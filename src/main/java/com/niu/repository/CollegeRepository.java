package com.niu.repository;

import com.niu.entity.CollegeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.niu.vo.CollegeNature;
import com.niu.vo.CollegeCategory;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface CollegeRepository  extends JpaRepository<CollegeEntity, Long> {


    @Query("SELECT c FROM CollegeEntity c WHERE (:provinceId IS NULL OR c.provinceId = :provinceId   ) " +
            "AND (:name IS NULL OR c.name LIKE %:name%) " +
            "AND (:category IS NULL OR c.category = :category) " +
            "AND (:nature IS NULL OR c.nature = :nature)")
    Page<CollegeEntity> getCollegesByCondition(Integer provinceId, String name, CollegeCategory category, CollegeNature nature, Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE CollegeEntity c SET c.provinceId = :provinceId WHERE c.id = :id")
    void updateCollegeProvinceIdById(Integer provinceId, Integer id);

    /**查询全部大学**/
    List<CollegeEntity> findAll();

    /**根据id查询大学**/
    CollegeEntity getCollegeById(Integer id);

    /**根据名称查询大学,模糊查询**/
    List<CollegeEntity> getCollegeByNameLike(String name);

    /**根据名称查询大学 **/
    CollegeEntity getCollegeByName(String name);

    /**根据省份查询大学**/
    Page<CollegeEntity> getCollegesByProvinceId(Integer provinceId, Pageable pageable);

    /**根据省份和类别查询大学**/
    Page<CollegeEntity> getCollegesByProvinceIdAndCategory(Integer provinceId, CollegeCategory category, Pageable pageable);

    /**根据省份、类别和招生类型查询大学**/
    Page<CollegeEntity> getCollegesByProvinceIdAndCategoryAndNature(Integer provinceId, CollegeCategory category, CollegeNature nature, Pageable pageable);

    /**按分数倒序根据省份、类别和招生类型查询大学**/
    @Query(value = "select * from acl_college where provinceId = ?1 and category = ?2 and type = ?3 order by score desc limit 0, 10", nativeQuery = true)
    List<CollegeEntity> findCollegesByProvinceIdAndCategoryAndTypeOrderByScoreDesc();

    /**通过学校查专业**/
    @Query(value = "select * from acl_major where collegeId = ?1", nativeQuery = true)
    List<CollegeEntity> findMajorByCollegeId(Integer collegeId);


    @Query(value = "select * from acl_score_record where collegeId = ?1", nativeQuery = true)
    List<CollegeEntity> findScoreRecordByCollegeId(Integer collegeId);

}
