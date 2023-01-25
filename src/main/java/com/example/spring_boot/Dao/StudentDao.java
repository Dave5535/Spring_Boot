package com.example.spring_boot.Dao;

import com.example.spring_boot.Entity.Student;

import java.util.Collection;
import java.util.Optional;

public interface StudentDao {

    Student persist(Student student);

    Optional<Student> findById(String id);

    Optional<Student> findByEmail(String email);

    Collection<Student> findByNameContains(String name);

    Collection<Student> findAll();

    Student update(Student student);

    void remove(String id);
}
