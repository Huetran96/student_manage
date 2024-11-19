<%@ page import="com.servlet.studentmanage.entity.Classroom" %>
<%@ page import="com.servlet.studentmanage.entity.Subject" %><%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 11/18/2024
  Time: 10:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Subject c = (Subject) request.getAttribute("subject"); %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<form method="post" action="?action=edit">
    <input type="hidden" name="id" value="<%= c.getId() %>"/>
    <label>Name: </label>
    <input name="name" value="<%= c.getName() %>" />
    <label>Hours: </label>
    <input name="hours" value="<%= c.getHours()%>" />
    <button type="submit">Submit</button>
</form>

</body>
</html>
