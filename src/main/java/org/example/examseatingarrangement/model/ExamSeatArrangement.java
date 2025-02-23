package org.example.examseatingarrangement.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ExamSeatArrangement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="exam_id")
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne
    @JoinColumn(name="seat_id")
    private Seat seat;

    public ExamSeatArrangement() {}

    public List<ExamSeatArrangement> arrangeSeats(Exam exam, StudentGroup studentGroup, Building building) {
        List<ExamSeatArrangement> arrangedSeats = new ArrayList<>();
        Room room = building.findRoomWithEnoughSeats(studentGroup.getStudents().size());
        List<Seat> seats = room.getSeats();
        for (int i = 0; i < studentGroup.getStudents().size(); i++) {
            User student = studentGroup.getStudents().get(i);
            Seat seat = seats.get(i);
            ExamSeatArrangement examSeatArrangement = new ExamSeatArrangement();
            examSeatArrangement.setExam(exam);
            examSeatArrangement.setStudent(student);
            examSeatArrangement.setSeat(seat);
            arrangedSeats.add(examSeatArrangement);
        }
        return arrangedSeats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
