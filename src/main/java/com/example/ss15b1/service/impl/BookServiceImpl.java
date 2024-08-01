package com.example.ss15b1.service.impl;

import com.example.ss15b1.dao.IBookDao;
import com.example.ss15b1.dao.impl.BookDaoImpl;
import com.example.ss15b1.entity.Book;
import com.example.ss15b1.service.IBookService;

import java.util.List;

public class BookServiceImpl implements IBookService {
    IBookDao bookDao=new BookDaoImpl();
    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public boolean addNewBook(Book book) {
        return bookDao.addNewBook(book);
    }

    @Override
    public boolean updateNewBook(Book book) {
        return bookDao.updateNewBook(book);
    }

    @Override
    public Book deleteId(Integer id) {
        return bookDao.deleteId(id);
    }

    @Override
    public Book findById(Integer id) {
        return bookDao.findById(id);
    }
}
