package com.re.session12.service;

import com.re.session12.model.entity.Student;
import com.re.session12.model.exception.StudentNotFoundException;
import com.re.session12.respository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student student) {
        Student existing = getStudentById(id);
        existing.setStudentCode(student.getStudentCode());
        existing.setFullName(student.getFullName());
        existing.setMajor(student.getMajor());
        existing.setGpa(student.getGpa());
        return studentRepository.save(existing);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}

