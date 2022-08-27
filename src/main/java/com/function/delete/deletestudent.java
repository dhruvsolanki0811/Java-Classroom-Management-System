package com.function.delete;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.admindao;

/**
 * Servlet implementation class deletestudent
 */
@WebServlet("/deletestudent")
public class deletestudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deletestudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String duser =request.getParameter("user");
		admindao user = new admindao();
		Connection conn = null;
try {
			
			conn=user.connect() ;
			if(conn!=null) {
				user.deleteuser(conn, duser);
			//user.closeConnection(conn);
			}
			else {
				System.out.println("Connection error");
			}
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} 
		finally {
			try {
				user.closeConnection(conn);
				response.sendRedirect("deletestudentform.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
	}

	}

