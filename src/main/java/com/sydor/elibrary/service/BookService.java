package com.sydor.elibrary.service;

import com.sydor.elibrary.entity.Book;

import java.util.List;

public interface BookService {

    Book save(Book book);

    void remove(Long id);

    Book find(Book book);

    List<Book> findAll();

    List<Book> getAllSorted();

    List<Book> findByName(String name);
}
