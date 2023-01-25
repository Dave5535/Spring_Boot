package com.example.spring_boot.Dao;

import com.example.spring_boot.Entity.Competence;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Optional;

@Repository
public class CompetenceImpl implements CompetenceDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Competence persist(Competence competence) {
        entityManager.persist(competence);
        return competence;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Competence> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Competence.class,id));
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Competence> findAll() {
        return entityManager.createQuery("select s from Course s", Competence.class).getResultList();
    }

    @Override
    @Transactional
    public Competence update(Competence competence) {
        return entityManager.merge(competence);
    }

    @Override
    @Transactional
    public void remove(Integer id) {
Competence student = entityManager.find(Competence.class,id);
entityManager.remove(student);
    }
}
