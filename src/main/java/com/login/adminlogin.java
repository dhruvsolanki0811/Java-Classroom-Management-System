package com.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.admindao;
import com.model.adminmodel;

@WebServlet("/adminlogin")
public class adminlogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public adminlogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		admindao user = new admindao();
		String parammail= request.getParameter("amail");
		String parampass= request.getParameter("apass");
		Connection conn = null;
		try {
			
			conn=user.connect() ;
			if(conn!=null) {
			if(user.validate(conn, parammail, parampass)) {
				HttpSession session = request.getSession();
				session.setAttribute("checklogin", "yes");
				adminmodel admin=user.getSession(conn,parammail);
				session.setAttribute("adminname", admin.getAdminname());
				session.setAttribute("adminid", admin.getAdminid());
				session.setAttribute("adminmail", parammail);
				response.sendRedirect("admindashboard.jsp");
			}else {
				response.sendRedirect("login.jsp");
			}
			//user.closeConnection(conn);
			}
			else {
				System.out.println("Connection error");
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				user.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
 
	
}
}