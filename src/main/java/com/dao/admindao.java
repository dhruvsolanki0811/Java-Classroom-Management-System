package com.dao;
import com.model.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class admindao {
	private  String url = "jdbc:mysql://localhost:3306/student";
	private  String username = "root";
	private  String password = "root";
	private  PreparedStatement ps;
	private  Connection con;
 /* public static void main(String args[]) throws SQLException {
	  boolean test;
		/*try {
		String sql = "select email,password from admin where email = ? and password =?";
		Class.forName("com.mysql.cj.jdbc.Driver");
	    con = DriverManager.getConnection(url, username, password);
	    ps = con.prepareStatement(sql);
	    ps.setString(1,"admin@gmail.com");
	    ps.setString(2,"pass");
	    ResultSet rs = ps.executeQuery();
	    rs.next();
	    System.out.print(rs.getString("email"));
	    System.out.print(rs.getString("password"));
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally {
			con.close();
		}*/
	/*  test = validate("admin@gmail.com","pass");
	  System.out.println(test);
	  
	}*/
	
	/*public  boolean validate(String em,String pass) throws SQLException
	{
		
		boolean status = false;
		String sql = "select email,password from admin where email = ? and password =?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    con = DriverManager.getConnection(url, username, password);
		    ps = con.prepareStatement(sql);
		    ps.setString(1, em);
		    ps.setString(2,pass);
		    ResultSet rs = ps.executeQuery();
		    status = rs.next();
		} catch (Exception e) {
		    // whatever you want to do to handle the exception
			e.printStackTrace();
		} finally {
		   
				//ps.close();
				con.close();
				
			
		}
		return status;
	}*/
	   public Connection connect() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
	        Connection conn;
	        String db_connect_str="jdbc:mysql://localhost:3306/student";
	        String db_userid ="root";
	        String db_password= "root";
	        //try
	        //{
	            Class.forName("com.mysql.jdbc.Driver").newInstance();

	         conn = DriverManager.getConnection(db_connect_str, db_userid, db_password);

//	        }
	       /* catch(Exception e)
	        {
	            e.printStackTrace();
	            //conn = null;
	        }*/

	        return conn;
	    }
	   
	   public boolean validate(Connection con,String em,String pass) throws SQLException
		{
			  PreparedStatement ps;
			  
			
			boolean status = false;
			String sql = "select email,password from admin where email = ? and password =?";
			try {
				/*Class.forName("com.mysql.cj.jdbc.Driver");
			   Connection con = connect(url,username,password)*/
			    ps = con.prepareStatement(sql);
			    ps.setString(1, em);
			    ps.setString(2,pass);
			    ResultSet rs = ps.executeQuery();
			    status = rs.next();
			} catch (Exception e) {
			    // whatever you want to do to handle the exception
				e.printStackTrace();
			} /*finally {
			   
					//ps.close();
					con.close();
					
				
			}*/
			return status;
		}
        public void closeConnection(Connection conn) throws SQLException
        {
        	if(conn!=null) {
        		conn.close();
        	}
         
        }

		public adminmodel getSession(Connection con,String em) {

				adminmodel user=new adminmodel(); //create new user object
			try
			{
				PreparedStatement ps = con.prepareStatement("select * from admin where email=?");//search database for email
				ps.setString(1, em);
				
				ResultSet rs = ps.executeQuery();
				
				if(rs.next())
				{
					user.setAdminid(rs.getInt("admin_id"));
					user.setAdminname(rs.getString("admin_name"));
					user.setEmail(rs.getString("email"));
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			return user;
		}

		public String newsubject(Connection conn, String cname, String cdesc) {
			String some="some error";
			try {
				PreparedStatement st = conn.prepareStatement("insert into subject values(?, ?)");
			    st.setString(1, cname);
			    st.setString(2,cdesc);
			    st.executeUpdate();
			    String s= "Class Created";
			    return s;
			} catch (SQLIntegrityConstraintViolationException e) {
			    // Duplicate entry
				String r="Class Name already exist";
				return r;
			} catch (SQLException e) {
			    // Other SQL Exception
				String r="Desc too long,maximum 250 characters";
				return r;
			} 
		}

		public String newstudent(Connection conn, String sname, String semail, String spass) {
			String some="some error";
			String check=insertuser(conn,semail);
			if(check.equals(semail)) {
			
			try {
				PreparedStatement st = conn.prepareStatement("insert into student values(null, ?,?,?)");
			    st.setString(1, sname);
			    st.setString(2,semail);
			    st.setString(3,spass);
			    st.executeUpdate();
			    String s= "Student registered";
			    return s;
			} catch (SQLException e) {
			    // Other SQL Exception
				String r="some error try again with valid username";
				return r;
			} }
			else {
				return check;
			}
		}

		private String insertuser(Connection conn, String semail) {
			try {
				PreparedStatement st = conn.prepareStatement("insert into user values(?)");
			    st.setString(1, semail);
			    st.executeUpdate();
			    return semail;
			}  catch (SQLIntegrityConstraintViolationException e) {
			    // Duplicate entry
				String r="Email already exist";
				return r;
			} 
			catch (SQLException e) {
			    return "some error";
				
			} 
		}
		public String newteacher(Connection conn, String sname, String semail, String spass) {
			
			String check=insertuser(conn,semail);
			if(check.equals(semail)) {
			
			try {
				PreparedStatement st = conn.prepareStatement("insert into teacher values(null, ?,?,?)");
			    st.setString(1, sname);
			    st.setString(2,semail);
			    st.setString(3,spass);
			    st.executeUpdate();
			    String s= "Teacher registered";
			    return s;
			} catch (SQLException e) {
			    // Other SQL Exception
				String r="some error try again with valid entries";
				return r;
			} }
			else {
				return check;
			}
		}

		public void deletesubject(Connection conn, String dclass) {
			// TODO Auto-generated method stub
			PreparedStatement st;
			try {
				st = conn.prepareStatement("delete from subject where subject_name=? ");
				st.setString(1, dclass);
			    st.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		public void deleteuser(Connection conn, String duser) {
			PreparedStatement st;
			try {
				st = conn.prepareStatement("delete from user where email=? ");
				st.setString(1, duser);
			    st.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}

		public String newannouncement(Connection conn, String mssg, String cclass, String cuser) {
			try {
				java.util.Date utilDate = new java.util.Date();
		        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		        Date time =new Date(utilDate.getTime());
		        DateFormat df=new SimpleDateFormat("H:mm");
		        String mytime=df.format(time);
				PreparedStatement st = conn.prepareStatement("insert into announcement values (null,?,?,?,?,?)");
			    st.setString(1, mssg);
			    st.setDate(2,sqlDate);
			    st.setString(3,mytime);
			    st.setString(4,cuser);
			    st.setString(5,cclass);
			    st.executeUpdate();
			    String s= "Announcement posted";
			    return s;
			}  catch (SQLException e) {
			    // Other SQL Exception
				e.printStackTrace();
				String r="Desc too long,maximum 350 characters";
				return r;
			} 
		}

		public void enrolluser(Connection conn, String euser, String classname) {
			PreparedStatement st;
			try {
				st = conn.prepareStatement("insert into enrolled values(?,?)");
				st.setString(1, classname);
				st.setString(2, euser);
			    st.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		public void newchat(Connection conn, String mssg, String cclass, String cuser) {
			try {
				java.util.Date utilDate = new java.util.Date();
		        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		        Date time =new Date(utilDate.getTime());
		        DateFormat df=new SimpleDateFormat("H:mm");
		        String mytime=df.format(time);
				PreparedStatement st = conn.prepareStatement("insert into chat values (null,?,?,?,?,?)");
			    st.setString(1, mssg);
			    st.setDate(2,sqlDate);
			    st.setString(3,mytime);
			    st.setString(4,cuser);
			    st.setString(5,cclass);
			    st.executeUpdate();
			}  catch (SQLException e) {
			    // Other SQL Exception
				e.printStackTrace();
			} 
		}

		public void removeuser(Connection conn, String ruser, String classname) {
			PreparedStatement st;
			try {
				st = conn.prepareStatement("delete from enrolled where subject_name=? and email=?");
				st.setString(1, classname);
				st.setString(2, ruser);
			    st.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	
		

	
	
} 
