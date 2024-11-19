package com.servlet.studentmanage.repository.impl;

import com.servlet.studentmanage.entity.Subject;
import com.servlet.studentmanage.repository.SubjectRepository;
import com.servlet.studentmanage.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SubjectRepositoryImpl implements SubjectRepository {
    @Override
    public List<Subject> findAll() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
           return session.createQuery("from Subject", Subject.class).list();
        }
    }

    @Override
    public Subject findById(Long id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Subject.class, id);
        }
    }

    @Override
    public void save(Subject subject) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(subject);
            transaction.commit();
        }catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Subject subject = session.get(Subject.class, id);
            if(subject == null) {
                throw new Exception("Subject not found");
            }
            session.delete(subject);
            transaction.commit();
        }catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public void update(Subject subject) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(subject);
            transaction.commit();
        }catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }
}
