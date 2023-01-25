package com.example.spring_boot.Dao;

import com.example.spring_boot.Entity.Course;

import java.util.Collection;
import java.util.Optional;

public interface CourseDao {
    Course persist(Course course);

    Optional<Course> findById(Integer id);

    Collection<Course> findAll();

    Course update(Course course);

    void remove(Integer id);
}
