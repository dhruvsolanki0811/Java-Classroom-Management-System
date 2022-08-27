package com.function.delete;

import java.io.IOException;
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


@WebServlet("/deleteclass")
public class deleteclass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteclass() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dclass =request.getParameter("class");
		admindao user = new admindao();
		Connection conn = null;
try {
			
			conn=user.connect() ;
			if(conn!=null) {
				user.deletesubject(conn, dclass);
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
				response.sendRedirect("deleteclassform.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
