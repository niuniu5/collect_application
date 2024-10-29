package com.niu.repository;

import com.niu.entity.StudentEntity;
import com.niu.vo.AcademicType;
import com.niu.vo.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {


    /** 获取学生信息 */
    StudentEntity getStudentById(Long id);
    /** 根据ID，更新学生信息 */

//  @Query("UPDATE AccountEntity a SET a.lastLogin =?2 WHERE a.id =?1")
    @Modifying
    @Transactional
    @Query("update StudentEntity s set s.name=:name,s.phone=:phone,s.gender=:gender,s.birthday=:birthday,s.provinceId=:provinceId,s.cityId=:cityId,s.areaId=:areaId,s.school=:school,s.interests=:interests,s.score=:score,s.academicType=:academicType,s.pluses=:pluses,s.subject=:subject,s.rank=:rank  WHERE s.id =:id")
    void updateStudent(@Param("id") Long id, @Param("name") String name, @Param("phone") String phone, @Param("gender") Gender gender,
                       @Param("birthday") Date birthday, @Param("provinceId") Integer provinceId, @Param("cityId") Integer cityId,
                       @Param("areaId") Integer areaId, @Param("school") String school, @Param("interests") String interests, @Param("score") Integer score,
                       @Param("academicType") AcademicType academicType, @Param("pluses") Integer pluses, @Param("subject") String subject, @Param("rank") Integer rank);

}
