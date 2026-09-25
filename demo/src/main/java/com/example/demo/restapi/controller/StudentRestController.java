package com.example.demo.restapi.controller;

import com.example.demo.restapi.model.entity.Student;
import com.example.demo.restapi.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentRestController {

    private final StudentService service;

    public StudentRestController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return service.getAll();
    }

    @GetMapping("/students/{id}")
    private Student getStudentById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student) {
        return service.create(student);
    }

    @PutMapping("/students/update/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return service.update(id, student);
    }
}
