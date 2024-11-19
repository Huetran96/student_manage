<%@ page import="com.servlet.studentmanage.entity.Classroom" %>
<%@ page import="java.util.List" %>
<%@ page import="com.servlet.studentmanage.entity.Subject" %><%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 11/18/2024
  Time: 9:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new student</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" >
</head>
<body>
<form method="post" action="?action=create">
    <div class="mb-3 col-6">
        <label for="name" class="form-label">Name:</label>
        <input type="text" class="form-control" id="name" placeholder="Enter name" name="name">
    </div>
    <div class="mb-3 mt-3 col-6">
        <label for="email" class="form-label">Email:</label>
        <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
    </div>
    <div class="mb-3 col-6">
        <label for="address" class="form-label">Address:</label>
        <input type="text" class="form-control" id="address" placeholder="Enter address" name="address">
    </div>
    <div class="mb-3 col-6">
        <label for="class">Class</label> <br/>
        <select id="class" name="classId">
            <% for(Classroom c : (List<Classroom>)request.getAttribute("classes")){ %>
            <option value="<%= c.getId()%>"> <%= c.getName() %> </option> <br/>
            <% } %>
        </select>
    </div>
    <div class="mb-3 col-6 ">
        <label>Subjects: </label>
        <div class="form-check ">
            <% for(Subject s : (List<Subject>)request.getAttribute("subjects")){ %>

            <label>
                <input class="form-check-input" type="checkbox" name="subjects" value="<%=s.getId()%>" >
                <%=s.getName()%>
            </label> <br/>

            <% } %>
        </div>


    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>

</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" >

</script>
</html>
