package com.example.ss15b1.service;

import com.example.ss15b1.entity.Book;
import com.example.ss15b1.entity.Category;

import java.util.List;

public interface IBookService {
    List<Book> findAll();

    boolean addNewBook(Book book);

    boolean updateNewBook(Book book);

    Book deleteId(Integer id);

    Book findById(Integer id);
}
