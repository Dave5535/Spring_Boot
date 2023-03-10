package com.example.spring_boot.Dao;

import com.example.spring_boot.Entity.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Optional;

@Repository
public class BookImpl implements BookDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Book persist(Book book) {
        entityManager.persist(book);
        return book;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Book> findAll() {
        return entityManager.
                createQuery("select b from Book b", Book.class)
                .getResultList();
    }

    @Override
    @Transactional
    public Book update(Book book) {
        return entityManager.merge(book);
    }

    @Override
    @Transactional
    public void remove(Integer id) {
        entityManager.remove( entityManager.find(Book.class, id));
    }
}
