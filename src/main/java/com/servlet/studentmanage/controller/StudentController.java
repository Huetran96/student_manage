package com.servlet.studentmanage.controller;

import com.servlet.studentmanage.entity.Classroom;
import com.servlet.studentmanage.entity.Student;
import com.servlet.studentmanage.entity.Subject;
import com.servlet.studentmanage.repository.ClassRepository;
import com.servlet.studentmanage.service.ClassService;
import com.servlet.studentmanage.service.StudentService;
import com.servlet.studentmanage.service.SubjectService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/student")
public class StudentController extends HttpServlet {
    private StudentService studentService;
    private ClassService classService;
    private SubjectService subjectService;

    @Override
    public void init() throws ServletException {
        super.init();
        studentService = new StudentService();
        classService = new ClassService();
        subjectService = new SubjectService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null || action.isEmpty()) action = "list";
        switch (action) {
            case "list": getList(req, resp); break;
            case "create": getCreate(req, resp); break;
            case "edit": getEdit(req, resp); break;
            default: resp.sendRedirect("student?action=list");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "create": postCreate(req, resp); break;
        }
    }

    private void getList(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        List<Student> students = studentService.findAll();
        req.setAttribute("students", students);
        req.getRequestDispatcher("/student/list.jsp").forward(req, resp);
    }
    private void getCreate(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        List<Classroom> classes = classService.findAll();
        List<Subject> subjects = subjectService.findAll();
        req.setAttribute("subjects", subjects);
        req.setAttribute("classes", classes);
        req.getRequestDispatcher("/student/create.jsp").forward(req, resp);
    }
    private void getEdit(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        try {
            Student student = studentService.findById(id);
            req.setAttribute("student", student);
            req.getRequestDispatcher("/student/edit.jsp").forward(req, resp);
        }catch (Exception e) {
            resp.sendRedirect("/student?action=list");
            e.printStackTrace();
        }
    }
    private void postCreate(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        try {
            List<Subject> subjects =new ArrayList<>();
            Long classId = Long.parseLong(req.getParameter("classId"));
            Classroom classroom = classService.findById(classId);

            String[] subjectChecked = req.getParameterValues("subjects");
            for (String s : subjectChecked) {
                Long subjectId = Long.parseLong(s);
                Subject subject = subjectService.findById(subjectId);
                subjects.add(subject);
            }

            Student student = new Student();
            student.setName(req.getParameter("name"));
            student.setEmail(req.getParameter("email"));
            student.setAddress(req.getParameter("address"));
            student.setClassroom(classroom);
            student.setSubjects(subjects);
            studentService.save(student);
            getList(req, resp);
        }catch (Exception e) {
            getCreate(req,resp);
        }

    }
}
