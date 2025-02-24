package org.example.examseatingarrangement.repository;

import org.example.examseatingarrangement.model.ExamSeatArrangement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamSeatArrangementRepository extends JpaRepository<ExamSeatArrangement, Long> {
}