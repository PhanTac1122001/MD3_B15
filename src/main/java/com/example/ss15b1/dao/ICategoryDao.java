package com.example.ss15b1.dao;

import com.example.ss15b1.entity.Category;

import java.util.List;

public interface ICategoryDao {
    List<Category> findAll();

    boolean addNewCategory(Category category);

    boolean updateNewCategory(Category category);

    Category deleteId(Integer id);

    Category findById(Integer id);



}
