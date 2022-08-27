<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC “-//W3C//DTD HTML 4.01//EN”
“http://www.w3.org/TR/html4/strict.dtd">

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>admin dashboard</title>
    <link rel="stylesheet" href="createclassform.css">
    <script src="admin_dashboard.js"></script>
</head>
<body>
<%
response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
if(session.getAttribute("checklogin")==null){
	response.sendRedirect("login.jsp");
}
%>
 <div>
          <h1 align='centre' style="color:white">Welcome <%=session.getAttribute("adminname") %> :)</h1>
            </div>
<div class="header"></div>
<input type="checkbox" id="openSidebarMenu">
<label for="openSidebarMenu" class="sidebarIconToggle">
    <div class="spinner top"></div>
    <div class="spinner middle"></div>
    <div class="spinner bottom"></div>
</label>
<div id="sidebarMenu">
<ul class="menu">
        <li><a class="menu-odd" >Classroom</a></li>
        <li><a href="admindashboard.jsp" id="Home">Home</a></li>
        <li><a href="admin_course.jsp" id="Course"> <%=session.getAttribute("classaccessed") %> Dashboard</a></li>
        <li><a href="createannouncement.jsp" id="CreateAnnouncement">Create Announcement</a></li>
        <li><a href="teachernotenrolled.jsp" id="AddNewTeacher">Add Teacher</a></li>
        <li><a href="teacherenrolled.jsp" id="RemovingTeacher">Remove Teacher</a></li>
        <li><a href="studentnotenrolled.jsp" id="AddNewStudent">Add Student</a></li>
	    <li><a href="studentenrolled.jsp" id="RemovingStudent">Remove Student</a></li>
	    <li><a href="logout">logout</a></li>
    </ul>  
</div>




 <main>
     <div class="CreateClass">
    <div class="ClassForm"></br>
        <img src="class.png" alt="">
        <form action="createannouncement" method='post'>
       <textarea name="message" style= "height: 100px; width: 400px;"     onclick="this.value=''">enter your announcement</textarea>
           </br> <button type="submit" class="button">Create</button></br>
            <%if(session.getAttribute("announcementposted")!=null){%>
            <span style="color:red;" ><%=session.getAttribute("announcementposted") %></span>
            <%session.removeAttribute("announcementposted");
            }%>
        </form>
    </div>
</div></main>

</body>

<script>
</script>


</html>