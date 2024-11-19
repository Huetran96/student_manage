package com.servlet.studentmanage.repository;

import com.servlet.studentmanage.entity.Subject;

import java.util.List;

public interface SubjectRepository {
    List<Subject> findAll();
    Subject findById(Long id);
    void save(Subject subject);
    void delete(Long id);
    void update(Subject subject);
}
