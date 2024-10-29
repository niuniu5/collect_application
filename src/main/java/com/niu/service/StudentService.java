package com.niu.service;

import com.niu.model.Account;
import com.niu.model.Student;

public interface StudentService {
    void saveStudent(Student student);
    Student getStudentById(Long id);
    void updateStudent(Student student);

}
