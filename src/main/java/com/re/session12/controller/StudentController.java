package com.re.session12.controller;

import com.re.session12.model.entity.Student;
import com.re.session12.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        logger.info("GET /api/students called");
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        logger.info("GET /api/students/{} called", id);
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        logger.info("POST /api/students called");
        Student saved = studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        logger.info("PUT /api/students/{} called", id);
        return ResponseEntity.ok(studentService.updateStudent(id, student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        logger.info("DELETE /api/students/{} called", id);
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
