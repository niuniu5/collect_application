package com.niu.repository;
import com.niu.entity.FillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FillRepository extends JpaRepository<FillEntity, Long> {
    List<FillEntity> findByStudentId(Long studentId);
}
