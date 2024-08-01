package com.example.ss15b1.dao.impl;

import com.example.ss15b1.dao.IBookDao;
import com.example.ss15b1.entity.Book;

import com.example.ss15b1.entity.Category;
import com.example.ss15b1.utils.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements IBookDao {
    @Override
    public List<Book> findAll() {
        Connection connection = ConnectionDB.openConnection();
        List<Book> books = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from book");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setCategoryId(rs.getInt("category_id"));
                book.setName(rs.getString("name"));
                book.setPrice(rs.getDouble("price"));
                book.setStock(rs.getInt("stock"));
                book.setTotalPages(rs.getInt("totalPages"));
                book.setYearCreated(rs.getInt("yearCreated"));
                book.setAuthor(rs.getString("author"));
                book.setImage(rs.getString("image"));
                book.setStatus(rs.getBoolean("status"));
                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return books;

    }

    @Override
    public boolean addNewBook(Book book) {
        Connection connection=ConnectionDB.openConnection();
        try {
            PreparedStatement ps=connection.prepareStatement("insert into book(category_id,name,price,stock,totalPages,yearCreated,author,image,status)values (?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, book.getCategoryId());
            ps.setString(2,book.getName());
            ps.setDouble(3,book.getPrice());
            ps.setInt(4,book.getStock());
            ps.setInt(5,book.getTotalPages());
            ps.setInt(6,book.getYearCreated());
            ps.setString(7,book.getAuthor());
            ps.setString(8,book.getImage());
            ps.setBoolean(9,book.getStatus());
            ps.executeUpdate();
            return true;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        finally {
            ConnectionDB.closeConnection(connection);
        }

    }

    @Override
    public boolean updateNewBook(Book book) {
        Connection connection=ConnectionDB.openConnection();
        try {
            PreparedStatement sql=connection.prepareStatement("update from book(category_id=?,name=?,price=?,stock=?,totalPages=?,yearCreated=?,author=?,image=?,status=?) where id=?");
            sql.setInt(1,book.getCategoryId());
            sql.setString(2,book.getName());
            sql.setDouble(3,book.getPrice());
            sql.setInt(4,book.getStock());
            sql.setInt(5,book.getTotalPages());
            sql.setInt(6,book.getYearCreated());
            sql.setString(7,book.getAuthor());
            sql.setString(8,book.getImage());
            sql.setBoolean(9,book.getStatus());
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
    public Book deleteId(Integer id) {
        Connection connection=ConnectionDB.openConnection();
        try {
            PreparedStatement ps=connection.prepareStatement("delete from book where id=?");
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
    public Book findById(Integer id) {
        Connection connection=ConnectionDB.openConnection();
        try {
            PreparedStatement ps=connection.prepareStatement(("select * from book where id=?"));
            ps.setInt(1,id);
            ResultSet rs= ps.executeQuery();
            if(rs.next()){
                Book book=new Book();
                book.setId(rs.getInt("id"));
                book.setId(rs.getInt("category_id"));
                book.setName(rs.getString("name"));
                book.setPrice(rs.getDouble("price"));
                book.setStock(rs.getInt("stock"));
                book.setTotalPages(rs.getInt("totalPages"));
                book.setYearCreated(rs.getInt("yearCreated"));
                book.setAuthor(rs.getString("author"));
                book.setImage(rs.getString("image"));
                book.setStatus(rs.getBoolean("status"));
                return book;
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
}
