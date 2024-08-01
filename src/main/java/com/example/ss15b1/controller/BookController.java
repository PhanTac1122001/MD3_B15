package com.example.ss15b1.controller;

import com.example.ss15b1.entity.Book;
import com.example.ss15b1.entity.Category;
import com.example.ss15b1.service.IBookService;
import com.example.ss15b1.service.ICategoryService;
import com.example.ss15b1.service.impl.BookServiceImpl;
import com.example.ss15b1.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/book")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 25
)
public class BookController extends HttpServlet {

    IBookService bookService=new BookServiceImpl();
    ICategoryService categoryService=new CategoryServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getParameter("action");
        action=action==null?"":action;
        switch (action){
            case "add":
                List<Category> categories=new CategoryServiceImpl().findAll();
                req.setAttribute("categories",categories);
                req.getRequestDispatcher("/WEB-INF/views/book/add_books.jsp").forward(req,resp);
                break;
            case "edit": {
                Integer id = Integer.parseInt(req.getParameter("id"));
                Book book = bookService.findById(id);
                req.setAttribute("book", book);
                req.getRequestDispatcher("/WEB-INF/views/book/edit_books.jsp").forward(req, resp);
                break;
            }
            case "detail": {
                Integer id = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("detail",bookService.findById(id));
                req.getRequestDispatcher("/WEB-INF/views/book/detail_books.jsp").forward(req, resp);
                break;
            }
            case "delete":
                Integer id=Integer.parseInt(req.getParameter("id"));
                bookService.deleteId(id);
                resp.sendRedirect("/book");
                break;
            default:
                req.setAttribute("book",bookService.findAll());
                req.getRequestDispatcher("/WEB-INF/views/book/books.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action=req.getParameter("action");
        action=action==null?"":action;
        switch (action){
            case "add":
            {
                Integer category= Integer.valueOf(req.getParameter("categoryId"));
                String name=req.getParameter("name");
                Double price= Double.valueOf(req.getParameter("price"));
                int stock=Integer.parseInt(req.getParameter("stock"));
                int totalPages=Integer.parseInt(req.getParameter("totalPages"));
                int yearCreated=Integer.parseInt(req.getParameter("yearCreated"));
                String author=req.getParameter("author");
                String pathUpload = req.getServletContext().getRealPath("/upload/");
                File fileUpload = new File(pathUpload);
                if (!fileUpload.exists()) {
                    fileUpload.mkdir();
                }

                // Xử lý upload ảnh
                Part part = req.getPart("image");
                String fileName = part.getSubmittedFileName();
                String image = "/upload/" + fileName;
                part.write(pathUpload + File.separator + fileName);
                Boolean status=Boolean.parseBoolean(req.getParameter("status"));
                Book book=new Book(null,category,name,price,stock,totalPages,yearCreated,author,image,status);
                bookService.addNewBook(book);
                resp.sendRedirect("/book");
                break;}
            case "update":
                Integer id=Integer.parseInt(req.getParameter("id"));
                String name=req.getParameter("name");
                Boolean status=Boolean.parseBoolean(req.getParameter("status"));
                Category category=new Category(id,name,status);
                categoryService.updateNewCategory(category);
                resp.sendRedirect("/category");
                break;

            default:
                req.setAttribute("category",categoryService.findAll());
                req.getRequestDispatcher("/WEB-INF/views/category/categorys.jsp").forward(req,resp);
        }
    }
}
