package com.servlet.studentmanage.repository;

import com.servlet.studentmanage.entity.Classroom;

import java.util.List;

public interface ClassRepository {
    void save(Classroom classroom);
    List<Classroom> findAll();
    Classroom findById(Long id);
    Classroom findByName(String name);
    void update( Classroom classroom);
    void delete(Long id);

}
