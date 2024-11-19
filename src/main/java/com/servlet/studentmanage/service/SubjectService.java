package com.servlet.studentmanage.service;

import com.servlet.studentmanage.entity.Subject;
import com.servlet.studentmanage.repository.SubjectRepository;
import com.servlet.studentmanage.repository.impl.SubjectRepositoryImpl;

import java.util.List;

public class SubjectService {
    private final SubjectRepository subjectRepository;
    public SubjectService() {
        subjectRepository = new SubjectRepositoryImpl();
    }

    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }
    public Subject findById(Long id) {
        return subjectRepository.findById(id);
    }
    public void save(Subject subject) throws Exception {
        if (subject.getName() == null) {
            throw new Exception("Name cannot be null");
        }
        subjectRepository.save(subject);
    }
    public void update(Subject subject) throws Exception {
        if (subject.getName() == null) {
            throw new Exception("Name cannot be null");
        }
        subjectRepository.update(subject);
    }
    public void deleteById(Long id) {
        subjectRepository.delete(id);
    }
}
