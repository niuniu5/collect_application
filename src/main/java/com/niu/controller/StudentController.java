package com.niu.controller;

import com.niu.model.Account;
import com.niu.model.Student;
import com.niu.service.AccountService;
import com.niu.service.StudentService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class StudentController {

    @Resource
    private StudentService studentService;


    /** 个人信息保存 **/
    @PostMapping("/api/saveStudent")
    public ResponseEntity<String> saveStudent(@RequestBody Student student){
        Long id = student.getId();
        Student studentEntity =  studentService.getStudentById(id);
        if (studentEntity == null) {
            studentService.saveStudent(student);
        }else{
            studentService.updateStudent(student);
        }
        return new ResponseEntity<>("saveStudent successful", HttpStatus.CREATED);
    }

    @GetMapping("/api/student/{id}")
    public Student getUserById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    /**个人信息更新**/
    /**
    @PostMapping("/account/updateStudent")
    public ResponseEntity<String> updateAccount(@RequestBody Student student){
        return studentService.updateStudent(student);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/api/user/{id}")
    public Student updateUser(@PathVariable Long id, @RequestBody Student user) {
        return studentService.updateUser(user);
    }

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @Autowired
    private UserService userService;

    @GetMapping("/student/{id}")
    public Student getUserById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student user) {
        return studentService.updateStudent(id, user);
    }
    **/
}
