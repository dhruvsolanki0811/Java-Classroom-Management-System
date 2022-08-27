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
    <link rel="stylesheet" href="delete.css">
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
        <li><a href="createclassform.jsp" id="CreateNewClass">Create class</a></li>
        <li><a href="deleteclassform.jsp" id="DeletingClass">Delete Class</a></li>
        <li><a href="createteacherform.jsp" id="CreateNewTeacher">Create Teacher</a></li>
        <li><a href="deleteteacherform.jsp" id="DeletingTeacher">Delete Teacher</a></li>
        <li><a href="createstudent.jsp" id="CreateNewStudent">Create Student</a></li>
	    <li><a href="deletestudentform.jsp" id="DeletingStudent">Delete Student</a></li>
	    <li><a href="logout">logout</a></li>
    </ul>
</div>





 <main>
     <div class="CreateStudent">
    <div class="StudentForm"></br>
     <table border = "01" width = "100%">
<tr>
    <th>Teacher id</th>
    <th>Teacher Name</th>
    <th>Email</th>
    <th>Delete</th>
</tr>
<%  
							    Connection con;
                                PreparedStatement pst;
                                ResultSet rs;
        
                                Class.forName("com.mysql.jdbc.Driver");
                                con = DriverManager.getConnection("jdbc:mysql://localhost/student","root","root");
                                
                                  String query = "select * from teacher";
                                  Statement st = con.createStatement();
                                  
                                    rs =  st.executeQuery(query);
                                    int num=1;
                                        while(rs.next())
                                        {
                                   %>
 <tr>
 				 <td><%=num%></td>
               <td><%=rs.getString("teacher_name") %></td>
                 <td><%=rs.getString("email") %></td>
     			<td><a href="deleteteacher?user=<%=rs.getString("email") %>" >Delete</a></td>
     
</tr>

<% num++;}
con.close(); %>

</div>
</div>
</main>

</body>

<script>

</script>


</html>