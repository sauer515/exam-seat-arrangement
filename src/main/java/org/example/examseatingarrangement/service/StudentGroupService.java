package org.example.examseatingarrangement.service;

import org.example.examseatingarrangement.model.StudentGroup;

import java.util.List;

public interface StudentGroupService {
    List<StudentGroup> findAll();
    StudentGroup findById(Long id);
    StudentGroup save(StudentGroup studentGroup);
    void deleteById(Long id);
}