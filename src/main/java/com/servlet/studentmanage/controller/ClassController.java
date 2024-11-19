package com.servlet.studentmanage.controller;

import com.servlet.studentmanage.entity.Classroom;
import com.servlet.studentmanage.service.ClassService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/class")
public class ClassController extends HttpServlet {
    private ClassService classService;

    @Override
    public void init() throws ServletException {
        super.init();
        classService = new ClassService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null || action.isEmpty()) action = "list";
        switch (action){
            case "create": getCreate(req, resp);
                break;
            case "edit": getEdit(req, resp);
                break;
            case "list": getShowList(req, resp);
                break;
            default: resp.sendRedirect("class?action=list");
                break;

        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action){
            case "create": postCreate(req, resp);
                break;
            case "edit": postEdit(req,resp);
                break;
            case "delete": postDelete(req,resp);
                break;
            default: resp.sendRedirect("class?action=list");
                break;
        }
    }

    private void getShowList(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        List<Classroom> classrooms = classService.findAll();
        req.setAttribute("classes", classrooms);
        req.getRequestDispatcher("class/list.jsp").forward(req, resp);
    }
    private void getEdit(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            Classroom classroom = classService.findById(id);
            req.setAttribute("class", classroom);
            req.getRequestDispatcher("class/edit.jsp").forward(req, resp);
        } catch (Exception e) {
            resp.sendRedirect("class?action=list");
        }
    }
    private void getCreate(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException{
        req.getRequestDispatcher("class/create.jsp").forward(req, resp);
    }

    private void postCreate(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException{
        try {
            String name = req.getParameter("name");
            Classroom classroom = new Classroom();
            classroom.setName(name);
            classService.save(classroom);
            resp.sendRedirect("class?action=list");
        }catch (Exception e) {
            getCreate(req, resp);
        }
    }
    private void postEdit(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException{
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        try {
            Classroom classroom = classService.findById(id);
            if(classroom == null){
                throw new Exception("Classroom not found!");
            }
            classroom.setName(name);
            classService.update(classroom);
            resp.sendRedirect("class?action=list");
        }catch (Exception e) {
            getEdit(req, resp);
        }
    }
    private void postDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        classService.deleteById(id);
        resp.sendRedirect("class?action=list");
    }
}
