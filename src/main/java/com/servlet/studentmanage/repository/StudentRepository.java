package com.servlet.studentmanage.repository;

import com.servlet.studentmanage.entity.Student;

import java.util.List;

public interface StudentRepository {
    void save(Student student);
    List<Student> findAll();
    Student findById(Long id);
    void update(Student student);
    void delete(Long id);

}
