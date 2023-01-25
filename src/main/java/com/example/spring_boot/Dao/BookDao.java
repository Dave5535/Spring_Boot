package com.example.spring_boot.Dao;

import com.example.spring_boot.Entity.Book;

import java.util.Collection;
import java.util.Optional;

public interface BookDao {
    Book persist(Book book);

    Optional<Book> findById(Integer id);

    Collection<Book> findAll();

    Book update(Book book);

    void remove(Integer id);
}
