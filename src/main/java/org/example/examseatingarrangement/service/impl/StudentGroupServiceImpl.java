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
}