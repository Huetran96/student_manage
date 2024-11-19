<%@ page import="com.servlet.studentmanage.entity.Student" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 11/18/2024
  Time: 9:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit students</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" >
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" >

    </script>
</head>
<body>
<h1>Edit student</h1>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Id</th>
        <th scope="col">Name</th>
        <th scope="col">Address</th>
        <th scope="col">Email</th>
        <th scope="col">Class</th>
        <th scope="col">No of subjects</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <% for(Student s : (List<Student>)request.getAttribute("students")){ %>
    <tr>
        <th scope="row"><%= s.getId() %></th>
        <td ><%= s.getName() %></td>
        <td><%= s.getAddress() %></td>
        <td><%= s.getEmail() %></td>
        <td><%= s.getClassroom().getName() %></td>
        <td><%= s.getSubjects().size() %></td>
        <%--        <td><%= s.getAddress() %></td>--%>
        <td> <a href="#">edit</a></td>

    </tr>
    <% } %>

    </tbody>
</table>
<h3></h3>

</body>
</html>
