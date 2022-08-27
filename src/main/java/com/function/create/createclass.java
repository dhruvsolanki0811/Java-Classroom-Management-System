package com.function.create;

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


@WebServlet("/createclass")
public class createclass extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public createclass() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//jjjj
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		admindao user = new admindao();
		String cname= request.getParameter("class_name");
		String cdesc= request.getParameter("class_desc");
		Connection conn = null;
		HttpSession session = request.getSession();
try {
			
			conn=user.connect() ;
			if(conn!=null) {
					session.setAttribute("classcreated", user.newsubject(conn,cname,cdesc));}
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
			response.sendRedirect("createclassform.jsp");
		
	}}}
