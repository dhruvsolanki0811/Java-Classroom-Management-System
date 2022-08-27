package com.function.remove;

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

/**
 * Servlet implementation class remove
 */
@WebServlet("/remove")
public class remove extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public remove() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String ruser =request.getParameter("user");
		String redirect =request.getParameter("redirect");
		String classname=(String)session.getAttribute("classaccessed");
		Connection conn = null;
		admindao user = new admindao();
		
try {
			
			conn=user.connect() ;
			if(conn!=null) {
				user.removeuser(conn,ruser,classname);
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
				if(redirect.equals("student")) {
					response.sendRedirect("studentenrolled.jsp");}
				else {
					response.sendRedirect("teacherenrolled.jsp");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}