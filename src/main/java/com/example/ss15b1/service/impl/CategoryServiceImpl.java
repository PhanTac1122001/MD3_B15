package com.example.ss15b1.service.impl;

import com.example.ss15b1.dao.ICategoryDao;
import com.example.ss15b1.dao.impl.CategoryDaoImpl;
import com.example.ss15b1.entity.Category;
import com.example.ss15b1.service.ICategoryService;

import java.util.List;

public class CategoryServiceImpl implements ICategoryService {
    @Override
    public boolean updateNewCategory(Category category) {
        return categoryDao.updateNewCategory(category);
    }

    @Override
    public Category deleteId(Integer id) {
        return categoryDao.deleteId(id);
    }

    @Override
    public Category findById(Integer id) {
        return categoryDao.findById(id);
    }

    @Override
    public boolean addNewCategory(Category category) {
        return categoryDao.addNewCategory(category);
    }

    ICategoryDao categoryDao=new CategoryDaoImpl();
    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}
