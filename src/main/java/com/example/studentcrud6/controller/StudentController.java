package com.example.studentcrud6.controller;

import com.example.studentcrud6.model.Student;
import com.example.studentcrud6.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // إضافة طالب جديد
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedStudent = service.saveStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    // جلب كل الطلاب
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = service.getAllStudents();
        return ResponseEntity.ok(students);
    }

    // جلب طالب واحد
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = service.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    // تعديل بيانات الطالب
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = service.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent);
    }

    // حذف الطالب
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        String message = service.deleteStudent(id);
        return ResponseEntity.ok(message);
    }
}