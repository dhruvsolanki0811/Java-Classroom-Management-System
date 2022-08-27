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
    <title>Admin page</title>
    <link rel="stylesheet" href="admin_course.css">
    <%
response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
if(session.getAttribute("checklogin")==null){
	response.sendRedirect("login.jsp");
}
%>
</head>
<body>
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



<main align="centre">
<div class="content">
<div class="tabs">
  <div class="tab-2">
    <label for="tab2-1">Announcements</label>
    <input id="tab2-1" name="tabs-two" type="radio" checked="checked">
    <div>
      <div class="container">

				<%  
							    Connection con;
                                PreparedStatement pst;
                                ResultSet rs;
                                Class.forName("com.mysql.jdbc.Driver");
                                con = DriverManager.getConnection("jdbc:mysql://localhost/student","root","root");
                                
                                  String query = "Select ann.content as announced,ann.date as whendate,ann.time as whattime ,u.email as mail, aa.announcer " +
                                          "from announcement as ann " +
                                          "inner join user as u on u.email=ann.email " +
                                          "inner join  (select teacher_name as announcer,email from teacher union select admin_name,email from admin) as aa on aa.email=u.email " +
                                          "inner join subject on subject.subject_name=ann.subject_name " +
                                          "where ann.subject_name= '" +session.getAttribute("classaccessed") +
                                          "' ORDER BY announcement_id DESC";
                                  Statement st = con.createStatement();
                                  
                                    rs =  st.executeQuery(query);
                                    
                                        while(rs.next())
                                        {
                                   %>







                    <div class="received_msg">
                        <div class="received_withd_msg">
                            <p>
                                <span class="announcer"><Strong> <%=rs.getString("announcer") %></Strong> || <%=rs.getString("mail") %></span><%=rs.getString("announced") %>
                            <span class="time_date"> <%=rs.getString("whendate") %>   |   <%=rs.getString("whattime") %></span></div></p>
                    </div>
                    <%} con.close(); %>


 </div>
    </div>
  </div>










  <div class="tab-2">
    <label for="tab2-2">Chats</label>
<input id="tab2-2" name="tabs-two" type="radio"checked="checked">

      <div class="container">

				 <form action="chatting" method="post">
				<input type="submit"value="Send" style="float: right ;width: 10%;" >
<div style="overflow: hidden; padding-right: .5em;">
   <input type="text" placeholder="Write some message" name="chat" style="width:10000px;">
</div>
				 </form>

				<%  
							    
                                Class.forName("com.mysql.jdbc.Driver");
                                con = DriverManager.getConnection("jdbc:mysql://localhost/student","root","root");
                                
                                  String query1 = "Select ann.message as messaged,ann.date as whendate,ann.time as whattime ,u.email as mail, aa.announcer " +
                                          "from chat as ann " +
                                          "inner join user as u on u.email=ann.email " +
                                          "inner join  (select teacher_name as announcer,email from teacher union all select admin_name,email from admin union all select student_name,email from student) as aa on aa.email=u.email " +
                                          "inner join subject on subject.subject_name=ann.subject_name " +
                                          "where ann.subject_name= '" +session.getAttribute("classaccessed") +
                                          "' ORDER BY chat_id DESC";
                                  st = con.createStatement();
                                  
                                    rs =  st.executeQuery(query1);
                                    
                                        while(rs.next())
                                        {
                                   %>







                    <div class="received_msg">
                        <div class="received_withd_msg">
                            <p>
                                <span class="announcer"><Strong> <%=rs.getString("announcer") %></Strong> || <%=rs.getString("mail") %></span><%=rs.getString("messaged") %>
                            <span class="time_date"> <%=rs.getString("whendate") %>   |   <%=rs.getString("whattime") %></span></div></p>
                    </div>
                    <%} con.close(); %>
				




 </div>




  </div>
</div>
</main>
<script>


</script>
</body>
</html>