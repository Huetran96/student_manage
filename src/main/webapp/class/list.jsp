<%@ page import="com.servlet.studentmanage.entity.Classroom" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 11/18/2024
  Time: 9:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Classes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" >
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body>
<a href="?action=create">Create new class</a>
<h1>List Classes</h1>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Id</th>
        <th scope="col">Name</th>
        <th scope="col">No of students</th>
        <th scope="col"></th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <% for(Classroom c : (List<Classroom>)request.getAttribute("classes")){ %>
    <tr>
        <th scope="row"><%= c.getId() %></th>
        <td ><%= c.getName() %></td>
        <td><%= c.getStudents().size() %></td>
        <td><button class="btn btn-warning">
            <a href="?action=edit&id=<%= c.getId() %>">
                edit
            </a>
        </button></td>
        <td>
            <form method="post" action="?action=delete">
                <input type="hidden" name="id" value="<%= c.getId() %>" />
                <button onclick="return confirm('Are you sure delete?')" type="submit" class="btn btn-danger"> delete</button>
            </form>
        </td>

    </tr>
    <% } %>

    </tbody>
</table>

</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" >
</script>
</html>
