package com.example.ss15b1.dao.impl;

import com.example.ss15b1.dao.ICategoryDao;
import com.example.ss15b1.entity.Category;
import com.example.ss15b1.utils.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements ICategoryDao {
    @Override
    public boolean updateNewCategory(Category category) {
        Connection connection=ConnectionDB.openConnection();
        try {
            PreparedStatement sql=connection.prepareStatement("update category set name=?,status=? where id=?");
            sql.setString(1,category.getName());
            sql.setBoolean(2,category.getStatus());
            sql.setInt(3,category.getId());
            sql.executeUpdate();
            return true;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }
    }

    @Override
    public Category deleteId(Integer id) {
        Connection connection=ConnectionDB.openConnection();
        try {
            PreparedStatement ps=connection.prepareStatement("delete from category where id=?");
            ps.setInt(1,id);
            ps.executeUpdate();
            return null;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        finally {
            ConnectionDB.closeConnection(connection);
        }
    }

    @Override
    public Category findById(Integer id) {
        Connection connection=ConnectionDB.openConnection();
        try {
            PreparedStatement ps=connection.prepareStatement(("select * from category where id=?"));
            ps.setInt(1,id);
            ResultSet rs= ps.executeQuery();
            if(rs.next()){
                Category category=new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setStatus(rs.getBoolean("status"));
                return category;
            }else {
                return null;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        finally {
            ConnectionDB.closeConnection(connection);
        }
    }

    @Override
    public boolean addNewCategory(Category category) {
        Connection connection=ConnectionDB.openConnection();
        try {
            PreparedStatement sql=connection.prepareStatement("insert into category(name,status) values (?,?)");
            sql.setString(1,category.getName());
            sql.setBoolean(2,category.getStatus());
            sql.executeUpdate();
            return true;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        finally {
        ConnectionDB.closeConnection(connection);
        }

    }

    @Override
    public List<Category> findAll() {
        Connection connection = ConnectionDB.openConnection();
        List<Category> categories = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from category");
            ResultSet rs = ps.executeQuery();

           while (rs.next()){
               Category category = new Category();
               category.setId(rs.getInt("id"));
               category.setName(rs.getString("name"));
               category.setStatus(rs.getBoolean("status"));
               categories.add(category);
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return categories;
    }
}
