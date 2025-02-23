package org.example.examseatingarrangement.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "student_groups")
public class StudentGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "studentGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students;

    @OneToMany(mappedBy = "studentGroup", fetch = FetchType.EAGER)
    private List<Exam> exams = new ArrayList<>();

    public StudentGroup() {
    }

    public StudentGroup(String name, List<Student> students) {
        this.name = name;
        this.students = students;
    }

    public StudentGroup(String name) {
        this.name = name;
    }

    public void addStudent(Student student) {
        if (students.contains(student)) {
            System.out.println("Student already is in group");
        }
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public void removeAllStudents() {
        students.clear();
    }

    public void addExam(Exam exam) {
        exams.add(exam);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
