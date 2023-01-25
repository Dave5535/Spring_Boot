package com.example.spring_boot.Dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.spring_boot.Entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Optional;

@Repository
public class StudentImpl implements StudentDao {
@PersistenceContext           // inject EntityManager that is a dao class in Spring boot to @Repository and Database
    EntityManager entityManager;

    @Override
    @Transactional
    public Student persist(Student student) {
        entityManager.persist(student);
        return student;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Student> findById(String id) {
        return Optional.ofNullable( entityManager.find( Student.class,id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Student> findByEmail(String email) {
        return entityManager.createQuery
                        ("select s from Student s where UPPER(s.email) = UPPER(:email)",Student.class )
                .setParameter("email" , email )
                .getResultStream().findFirst();

    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Student> findByNameContains(String name) {
        return entityManager.createQuery("select  s from Student s where UPPER(s.firstName) LIKE UPPER(concat('%', :name ,'%'))",Student.class)
             .setParameter("name",name).getResultList();

    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Student> findAll() {
        return entityManager.
                createQuery("select s from Student s", Student.class)
                .getResultList();

    }

    @Override
    @Transactional
    public Student update(Student student) {
        return entityManager.merge(student);
    }

    @Override
    @Transactional
    public void remove(String id) {
        Student student = entityManager.find(Student.class,id);
        if(student != null) entityManager.remove(student);
//else // throw exception
    }
}
