package com.sgic.student.controller;

import com.sgic.student.entity.Student;
import com.sgic.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
//////////////////////////////////////////////
    @PostMapping("/addStudent")
    public Student addStudent(@RequestBody Student student){
        return studentRepository.save(student);
    }

    @GetMapping("/getAll")
    public List<Student> allStudent(){
        return studentRepository.findAll();
    }

    @GetMapping("getStudent/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id){
        Student student = studentRepository.findById(id).orElseThrow(null);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<Student> updateStrudent(@PathVariable int id, @RequestBody Student student){
        Student stu = studentRepository.findById(id).orElseThrow(null);
        stu.setName(student.getName());
        stu.setAge(student.getAge());
        stu.setAddress(student.getAddress());

        Student stu1 = studentRepository.save(stu);

        return ResponseEntity.ok(stu1);
    }
    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id){
        Student student = studentRepository.findById(id).orElseThrow(null);
        studentRepository.delete(student);
        return ResponseEntity.ok("Success");
    }



}
