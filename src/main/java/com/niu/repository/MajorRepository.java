package com.niu.repository;

import com.niu.entity.MajorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MajorRepository extends JpaRepository<MajorEntity, Long> {
    @Query("SELECT c FROM MajorEntity c WHERE (c.collegeId = :collegeId and c.name= :name) " )
    MajorEntity getMajorByCollegeIdAndName(Integer collegeId, String name);
    MajorEntity getMajorById(Integer id);
    MajorEntity getMajorByName(String name);
    @Query("SELECT c FROM MajorEntity c WHERE (c.collegeId = :collegeId) " )
    List<MajorEntity> getMajorByCollegeId(Integer collegeId);

}
