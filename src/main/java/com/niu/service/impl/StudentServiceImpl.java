package com.niu.service.impl;

import com.niu.entity.StudentEntity;
import com.niu.model.Student;
import com.niu.repository.StudentRepository;
import com.niu.service.StudentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentRepository studentRepository;

    @Override
    public void saveStudent(Student student) {
//        StudentEntity studentEntity = convertStudentEntity(student);
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(student.getId());
        studentEntity.setGender(student.getGender());
        studentRepository.save(studentEntity);
    }

    @Override
    public Student getStudentById(Long id) {
        StudentEntity studentEntity = studentRepository.getStudentById(id);
        return convertStudentEntity(studentEntity);
    }

    @Override
    public void updateStudent(Student student) {
        studentRepository.updateStudent(student.getId(),student.getName(),student.getPhone(),student.getGender(),
                student.getBirthday(),student.getProvinceId(),student.getCityId(),student.getAreaId(),student.getSchool(),
                student.getInterests(),student.getScore(),student.getAcademicType(),student.getPluses(),student.getSubject(),student.getRank());
    }


    /**public Student getStudentById(Long id) {
        return null;
    }

    public Student updateStudent(Long id, Student student) {
        return null;
    }


    public Student getStudentById(Integer id) {
        StudentEntity studentEntity = studentRepository.getStudentById(id);
        return convertStudentEntity(studentEntity);
    }


    public void updateStudent(Student student) {
        studentRepository.updateStudent(convertStudentEntity(student));
    }**/

    private StudentEntity convertStudentEntity(Student student) {
        return new StudentEntity(student.getId(),student.getName(),student.getPhone(),student.getGender(),
                student.getBirthday(),student.getProvinceId(),student.getCityId(),student.getAreaId(),student.getSchool(),student.getInterests(),student.getScore(),student.getAcademicType(),student.getPluses(),student.getSubject(),student.getRank());
    }

    private Student convertStudentEntity(StudentEntity student) {
        if (student == null) {
            return null;
        }else{
            return new Student(student.getId(),student.getName(),student.getPhone(),student.getGender(),
                    student.getBirthday(),student.getProvinceId(),student.getCityId(),student.getAreaId(),student.getSchool(),student.getInterests(),student.getScore(),student.getAcademicType(),student.getPluses(),student.getSubject(),student.getRank());
        }
    }
}
