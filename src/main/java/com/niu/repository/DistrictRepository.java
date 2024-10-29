package com.niu.repository;

import com.niu.entity.DistrictEntity;
import com.niu.entity.MajorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<DistrictEntity, Long> {

    DistrictEntity findByName(String provinceName);

    List<DistrictEntity> findByNameLike(String provinceName);

    List<DistrictEntity>  findAll();

    @Query(value = "SELECT c.parent FROM DistrictEntity c WHERE  c.id = :id")
    Integer getProvinceIdByCityId(int id);

}
