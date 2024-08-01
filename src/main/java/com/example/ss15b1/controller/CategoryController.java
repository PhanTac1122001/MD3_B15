package com.example.ss15b1.controller;

import com.example.ss15b1.entity.Category;
import com.example.ss15b1.service.ICategoryService;
import com.example.ss15b1.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/category")
public class CategoryController extends HttpServlet {
    ICategoryService categoryService=new CategoryServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getParameter("action");
        action=action==null?"":action;
        switch (action){
            case "add":
                req.getRequestDispatcher("/WEB-INF/views/category/add_categorys.jsp").forward(req,resp);
                break;
            case "edit": {
                Integer id = Integer.parseInt(req.getParameter("id"));
                Category category = categoryService.findById(id);
                req.setAttribute("category", category);
                req.getRequestDispatcher("/WEB-INF/views/category/edit_categorys.jsp").forward(req, resp);
                break;
            }
            case "detail": {
                Integer id = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("detail",categoryService.findById(id));
                        req.getRequestDispatcher("/WEB-INF/views/category/detail_categorys.jsp").forward(req, resp);
                break;
            }
            case "delete":
                Integer id=Integer.parseInt(req.getParameter("id"));
                categoryService.deleteId(id);
                resp.sendRedirect("/category");
                break;
            default:
                req.setAttribute("category",categoryService.findAll());
                req.getRequestDispatcher("/WEB-INF/views/category/categorys.jsp").forward(req,resp);
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
               String name=req.getParameter("name");
               Boolean status=Boolean.parseBoolean(req.getParameter("status"));
               Category category=new Category(null,name,status);
               categoryService.addNewCategory(category);
               resp.sendRedirect("/category");
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
