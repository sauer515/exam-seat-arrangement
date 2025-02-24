package org.example.examseatingarrangement.service;

import org.example.examseatingarrangement.model.Exam;

import java.util.List;

public interface ExamService {
    List<Exam> findAll();
    Exam findById(Long id);
    Exam save(Exam exam);
    void deleteById(Long id);
}