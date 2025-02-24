package org.example.examseatingarrangement.service;

import org.example.examseatingarrangement.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Student findById(Long id);
    Student save(Student student);
    void deleteById(Long id);
}