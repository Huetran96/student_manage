package com.servlet.studentmanage.controller;

import com.servlet.studentmanage.entity.Subject;
import com.servlet.studentmanage.service.SubjectService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/subject")
public class SubjectController extends HttpServlet {
    private SubjectService subjectService;

    @Override
    public void init() throws ServletException {
        super.init();
        subjectService = new SubjectService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null || action.isEmpty()) action = "list";
        switch (action) {
            case "list": getList(req, resp); break;
            case "create": getCreate(req, resp); break;
            case "edit": getEdit(req, resp); break;
            default: getList(req,resp); break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "create": postCreate(req, resp); break;
            case "edit": postEdit(req, resp); break;
            case "delete" : postDelete(req, resp); break;
            default: resp.sendError(HttpServletResponse.SC_BAD_REQUEST); break;
        }
    }

    private void getList(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Subject> subjects = subjectService.findAll();
        req.setAttribute("subjects", subjects);
        req.getRequestDispatcher("subject/list.jsp").forward(req, resp);
    }
    public void getEdit(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Subject subject = subjectService.findById(id);
        req.setAttribute("subject", subject);
        req.getRequestDispatcher("subject/edit.jsp").forward(req, resp);
    }
    public void getCreate(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        req.getRequestDispatcher("subject/create.jsp").forward(req,resp);
    }
    public void postCreate(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Subject subject = new Subject();
        try {
            subject.setName(req.getParameter("name"));
            Integer hours = Integer.parseInt(req.getParameter("hours"));
            subject.setHours(hours);
            subjectService.save(subject);
            getList(req, resp);
        }catch (Exception e) {
            getCreate(req, resp);
            e.printStackTrace();
        }
    }
    public void postEdit(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            Subject subject = subjectService.findById(id);
            if (subject == null) {
                throw new Exception("Subject not found!");
            }
            subject.setName(req.getParameter("name"));
            subject.setHours(Integer.parseInt(req.getParameter("hours")));
            subjectService.update(subject);
        }catch (Exception e) {
            getEdit(req, resp);
        }

    }
    public void postDelete(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        subjectService.deleteById(id);
        resp.sendRedirect("list");
    }
}
