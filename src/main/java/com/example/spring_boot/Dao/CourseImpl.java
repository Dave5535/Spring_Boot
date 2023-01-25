package com.example.spring_boot.Dao;

import com.example.spring_boot.Entity.Course;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Optional;

@Repository
public class CourseImpl implements CourseDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Course persist(Course course) {
        entityManager.persist(course);
        return course;
    }

    @Override
    public Optional<Course> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Course.class,id));
    }

    @Override
    public Collection<Course> findAll() {
        return entityManager.createQuery("select s from Course s", Course.class).getResultList();
    }

    @Override
    public Course update(Course course) {
        return entityManager.merge(course);
    }

    @Override
    public void remove(Integer id) {

        entityManager.remove(entityManager.find(Course.class,id));
    }
}
