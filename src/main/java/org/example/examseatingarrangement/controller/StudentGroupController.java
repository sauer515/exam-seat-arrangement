package org.example.examseatingarrangement.controller;

import org.example.examseatingarrangement.model.Exam;
import org.example.examseatingarrangement.model.Student;
import org.example.examseatingarrangement.model.StudentGroup;
import org.example.examseatingarrangement.service.ExamService;
import org.example.examseatingarrangement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.examseatingarrangement.service.StudentGroupService;

import java.util.List;

@RestController
@RequestMapping("api/student-group")
public class StudentGroupController {
    private final StudentGroupService studentGroupService;
    private final StudentService studentService;
    private final ExamService examService;

    @Autowired
    public StudentGroupController(StudentGroupService studentGroupService, StudentService studentService, ExamService examService) {
        this.studentGroupService = studentGroupService;
        this.studentService = studentService;
        this.examService = examService;
    }

    @GetMapping
    public List<StudentGroup> getAllStudentGroups() {
        return studentGroupService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentGroup> getStudentGroupById(@PathVariable Long id) {
        StudentGroup studentGroup = studentGroupService.findById(id);
        if (studentGroup == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(studentGroup);
        }
    }

    @PostMapping("/add")
    public StudentGroup addStudentGroup(@RequestParam String name, @RequestBody List<Student> students) {
        if (students == null || students.isEmpty()) {
            return studentGroupService.save(new StudentGroup(name));
        }
        StudentGroup studentGroup = new StudentGroup(name, students);
        return studentGroupService.save(studentGroup);
    }

    //todo add passwords hashing before saving

    @PostMapping("/addStudent")
    public ResponseEntity<StudentGroup> addStudent(@RequestParam Long id, @RequestBody Student student) {
        StudentGroup studentGroup = studentGroupService.findById(id);
        if (studentGroup == null) {
            return ResponseEntity.notFound().build();
        }
        if (student == null) {
            return ResponseEntity.badRequest().build();
        }
        student.setStudentGroup(studentGroup);
        studentGroup.addStudent(student);
        studentService.save(student);
        studentGroupService.save(studentGroup);
        return ResponseEntity.ok(studentGroup);
    }

    @PostMapping("/addExam")
    public ResponseEntity<StudentGroup> addExam(@RequestParam Long id, @RequestBody Exam exam) {
        StudentGroup studentGroup = studentGroupService.findById(id);
        if (studentGroup == null) {
            return ResponseEntity.notFound().build();
        }
        if (exam == null) {
            return ResponseEntity.badRequest().build();
        }
        exam.setStudentGroup(studentGroup);
        studentGroup.addExam(exam);
        examService.save(exam);
        studentGroupService.save(studentGroup);
        return ResponseEntity.ok(studentGroup);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudentGroup(@PathVariable Long id) {
        if (studentGroupService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        studentGroupService.deleteById(id);
        return ResponseEntity.ok("Student group successfully deleted");
    }

    @DeleteMapping("/{groupId}/student/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long groupId, @PathVariable Long studentId) {
        try {
            studentGroupService.removeStudentFromGroup(groupId, studentId);
            return ResponseEntity.ok("Student successfully deleted from group" + groupId);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{groupId}/exam/{examId}")
    public ResponseEntity<String> deleteExam(@PathVariable Long groupId, @PathVariable Long examId) {
        try {
            studentGroupService.removeExamFromGroup(groupId, examId);
            return ResponseEntity.ok("Exam successfully deleted from group");
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
