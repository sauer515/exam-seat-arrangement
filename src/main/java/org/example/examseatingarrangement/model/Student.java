package org.example.examseatingarrangement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student extends User {

    @ManyToOne
    @JoinColumn(name = "student_group_id")
    private StudentGroup studentGroup;

    public Student() {
    }

    public Student(String firstName, String lastName, String email, String password, StudentGroup studentGroup) {
        super(firstName, lastName, email, password);
        this.studentGroup = studentGroup;
    }

    public StudentGroup getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
    }
}