package com.example.studentcrud6.service;

import com.example.studentcrud6.model.Student;
import com.example.studentcrud6.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    // إنشاء طالب جديد
    public Student saveStudent(Student student) {
        return repository.save(student);
    }

    // جلب كل الطلاب
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    // جلب طالب واحد حسب الـ ID
    public Student getStudentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    // تحديث بيانات الطالب
    public Student updateStudent(Long id, Student student) {
        Student existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        
        existing.setName(student.getName());
        existing.setMajor(student.getMajor());
        existing.setGrade(student.getGrade());
        return repository.save(existing);
    }

    // حذف طالب
    public String deleteStudent(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        repository.deleteById(id);
        return "Student removed with id: " + id;
    }
}