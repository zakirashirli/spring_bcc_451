package com.example.demo.restapi.service;

import com.example.demo.restapi.exception.NotFoundException;
import com.example.demo.restapi.model.entity.Student;
import com.example.demo.restapi.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }


    public List<Student> getAll() {
        return repository.findAll();
    }

    public Student getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found with id: " + id));
    }

    public Student create(Student student) {
        return repository.save(student);
    }

    public Student update(Long id, Student newStudent) {

        Student student = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found with id: " + id));

        student.setName(newStudent.getName()); // name:oMAR -> newName: -> Omar
        student.setAge(newStudent.getAge()); // name:oMAR -> newName: -> Omar
        student.setEmail(newStudent.getEmail()); // name:oMAR -> newName: -> Omar

        return repository.save(student);
    }

}
