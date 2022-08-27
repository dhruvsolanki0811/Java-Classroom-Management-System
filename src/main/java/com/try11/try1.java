package com.try11;

import java.io.IOException;
import java.sql.*;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class try1
 */
@WebServlet("/try1")
public class try1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public try1() {
        super();
        // TODO Auto-generated constructor stub
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s=request.getParameter("classs");
		HttpSession session= request.getSession();
		session.setAttribute("classaccessed",s);
		response.sendRedirect("admin_course.jsp");
		//PrintWriter out= response.getWriter();
		//out.println(session.getAttribute("classaccessed") );
	}

}
