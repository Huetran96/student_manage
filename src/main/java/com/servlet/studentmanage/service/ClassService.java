package com.servlet.studentmanage.service;

import com.servlet.studentmanage.entity.Classroom;
import com.servlet.studentmanage.repository.ClassRepository;
import com.servlet.studentmanage.repository.impl.ClassRepositoryImpl;

import java.util.List;

public class ClassService {
    private final ClassRepository classRepository;
    public ClassService() {
        this.classRepository = new ClassRepositoryImpl();
    }
    public List<Classroom> findAll(){
        return classRepository.findAll();
    }
    public Classroom findById(Long id) throws Exception {
        Classroom classroom= classRepository.findById(id);
        if(classroom==null){
            throw new Exception("Classroom not found");
        }
        return classroom;
    }
    public Classroom findByName(String name) throws Exception {
        Classroom classroom= classRepository.findByName(name);
        if(classroom==null){
            throw new Exception("Classroom not found");
        }
        return classroom;
    }
    public void save(Classroom classroom) throws Exception {
        if (classroom.getName().isEmpty() ){
            throw new Exception("Name not be empty");
        }

        classRepository.save(classroom);
    }
    public void update(Classroom classroom) throws Exception {
        if (classroom.getName().isEmpty()){
            throw new Exception("Name not be empty");
        }
        classRepository.update(classroom);
    }
    public void deleteById(Long id){
        classRepository.delete(id);
    }
}
