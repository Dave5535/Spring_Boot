package com.example.spring_boot.Dao;

import com.example.spring_boot.Entity.Competence;

import java.util.Collection;
import java.util.Optional;

public interface CompetenceDao {
    Competence persist(Competence competence);

    Optional<Competence> findById(Integer id);

    Collection<Competence> findAll();

    Competence update(Competence competence);

    void remove(Integer id);
}
