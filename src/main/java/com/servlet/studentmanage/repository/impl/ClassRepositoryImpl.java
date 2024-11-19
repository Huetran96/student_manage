package com.servlet.studentmanage.repository.impl;

import com.servlet.studentmanage.entity.Classroom;
import com.servlet.studentmanage.repository.ClassRepository;
import com.servlet.studentmanage.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClassRepositoryImpl implements ClassRepository {
    @Override
    public List<Classroom> findAll() {
        try(Session session =HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Classroom c right join fetch c.students", Classroom.class).list();
        }
    }
    @Override
    public void save(Classroom classroom) {
        Transaction transaction = null;
        try(Session session =HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(classroom);
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Classroom findById(Long id) {
        try(Session session =HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Classroom.class, id);
        }
    }

    @Override
    public Classroom findByName(String name) {
        try(Session session =HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Classroom.class, name);
        }
    }

    @Override
    public void update(Classroom classroom) {
        Transaction transaction = null;
        try(Session session =HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(classroom);
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        try(Session session =HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Classroom classroom = session.get(Classroom.class, id);
            if (classroom == null) {
                throw new RuntimeException("Classroom not found");
            }
            session.delete(classroom);
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }


}
