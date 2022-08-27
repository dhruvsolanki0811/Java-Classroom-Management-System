package com.function.create;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.admindao;

/**
 * Servlet implementation class createannouncement
 */
@WebServlet("/createannouncement")
public class createannouncement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createannouncement() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		admindao user = new admindao();
		String mssg=request.getParameter("message");
		HttpSession session= request.getSession();
		String cclass=(String) session.getAttribute("classaccessed");
		String cuser =(String) session.getAttribute("adminmail");
		Connection conn = null;
try {
			
			conn=user.connect() ;
			if(conn!=null) {
					session.setAttribute("announcementposted", user.newannouncement(conn,mssg,cclass,cuser));}
			else{
				System.out.println("Connection error");
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			
		}catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally{
			try {
				user.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("createannouncement.jsp");
		
	}}
		//PrintWriter out= response.getWriter();
		//out.println(session.getAttribute("announcement") );
	

}
