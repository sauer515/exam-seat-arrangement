package org.example.examseatingarrangement.service.impl;

import org.example.examseatingarrangement.model.StudentGroup;
import org.example.examseatingarrangement.repository.StudentGroupRepository;
import org.example.examseatingarrangement.service.StudentGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentGroupServiceImpl implements StudentGroupService {

    @Autowired
    private StudentGroupRepository studentGroupRepository;

    @Override
    public List<StudentGroup> findAll() {
        return studentGroupRepository.findAll();
    }

    @Override
    public StudentGroup findById(Long id) {
        return studentGroupRepository.findById(id).orElse(null);
    }

    @Override
    public StudentGroup save(StudentGroup studentGroup) {
        return studentGroupRepository.save(studentGroup);
    }

    @Override
    public void deleteById(Long id) {
        studentGroupRepository.deleteById(id);
    }

    @Override
    public void removeStudentFromGroup(Long groupId, Long studentId) {
        StudentGroup studentGroup = studentGroupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Student group not found with id: " + groupId));

        boolean removed = studentGroup.getStudents().removeIf(student -> student.getId().equals(studentId));

        if (!removed) {
            throw new IllegalArgumentException("Student not found in group with id: " + studentId);
        }

        studentGroupRepository.save(studentGroup);
    }

    @Override
    public void removeExamFromGroup(Long groupId, Long examId) {
        StudentGroup studentGroup = studentGroupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Student group not found with id: " + groupId));

        boolean removed = studentGroup.getExams().removeIf(exam -> exam.getId().equals(examId));

        if (!removed) {
            throw new IllegalArgumentException("Exam not found in group with id: " + examId);
        }

        studentGroupRepository.save(studentGroup);
    }
}