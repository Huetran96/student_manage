package com.servlet.studentmanage.service;

import com.servlet.studentmanage.entity.Student;
import com.servlet.studentmanage.repository.StudentRepository;
import com.servlet.studentmanage.repository.impl.StudentRepositoryImpl;

import java.util.List;

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService() {
        this.studentRepository = new StudentRepositoryImpl();
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }
    public Student findById(Long id) throws Exception {
        Student student = studentRepository.findById(id);
        if (student == null) {
            throw new Exception("Student not found");
        }
        return student;
    }
    public void save(Student student) throws Exception {
        if (student.getName().isEmpty()){
            throw new Exception("Name is empty");
        }
        studentRepository.save(student);
    }
    public void update(Student student) throws Exception {
        if (student.getName().isEmpty()){
            throw new Exception("Name is empty");
        }
        studentRepository.update(student);
    }
    public void deleteById(Long id) throws Exception {
        studentRepository.delete(id);
    }
}
