package com.example.ss15b1.dao;

import com.example.ss15b1.entity.Book;
import com.example.ss15b1.entity.Category;

import java.util.List;

public interface IBookDao {
    List<Book> findAll();

    boolean addNewBook(Book book);

    boolean updateNewBook(Book book);

    Book deleteId(Integer id);

    Book findById(Integer id);
}
