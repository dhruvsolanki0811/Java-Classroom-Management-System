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
import com.model.adminmodel;


@WebServlet("/createteacher")
public class createteacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public createteacher() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		admindao user = new admindao();
		String sname= request.getParameter("t_name");
		String semail= request.getParameter("t_email");
		String spass= request.getParameter("t_pass");
		Connection conn = null;
		HttpSession session = request.getSession();
try {
			
			conn=user.connect() ;
			if(conn!=null) {
					session.setAttribute("teachercreated", user.newteacher(conn,sname,semail,spass));}
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
			response.sendRedirect("createteacherform.jsp");
	}}}