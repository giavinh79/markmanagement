
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VerifyLogin
 */
@WebServlet("/VerifyLogin")
public class VerifyLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String user = "";
	public static String pass = "";
	public static int personID = 1;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifyLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		user = request.getParameter("username");
		pass = request.getParameter("password");

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection myConn = DriverManager.getConnection("jdbc:mysql://mysql3000.mochahost.com:3306/giavinh7_mark","giavinh7_default","minimumpermission123");
			//Connection myConn = DriverManager.getConnection(
			//		"jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false", "default", "minimumpermission");
			Statement state = myConn.createStatement();
			String query = "SELECT * FROM student WHERE user_name LIKE " + "'" + user + "'";
			ResultSet r = state.executeQuery(query);
			query = "test";
			int f = 0;
			while (r.next()){
				if (r.getString("password").equals(pass))
				{
					f = 1;
					personID = r.getInt("student_id");
					break;
				}
			}

			if (f == 0)
			{
				response.sendRedirect("LoginFail.html");
				out.close();
			}
			
			/*
			query = "SELECT student_id FROM student WHERE user_name LIKE " + "'" + user + "'";
			ResultSet r2 = state.executeQuery(query);
			personID = 3;
			while (r2.next()){
				if (r2.getString("student_id").equals("1"))
				{
					f = 1;
					break;
				}
				personID = 2;
			} 
			*/
			//personID = r2.getInt(0);
			
			
			myConn.close();
			//response.sendRedirect("success.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("DynamicStudent");
			rd.forward(request,response);


			//state.executeUpdate("INSERT into student VALUES ('new', 'person', 'M', 0)");


			//out.println("<HTML><BODY>");
			//out.println("<H1>" + "SUCCESS! " + "Welcome " + user + "!" + "</H1>");
			//out.println("</BODY></HTML>");
		}
		catch (Exception ex)
		{
			//ex.printStackTrace();
			out.println("<HTML><BODY>");
			out.println("<H1>" + "Error: Login failed due to server or database issues." + "</H1>");
			out.println("</BODY></HTML>");
			out.close();
		}

		//NOTE: Had to add mysql-connector-java to both Tomcat LIB directory + Project Build Path
		//Ctrl+Shift+F to auto-format

		

	}

	//Ctrl+Shift+F auto-format
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		/*
		response.setContentType("text/html");
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		
		try
		{
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false",user,pass);
			
			//Statement state = myConn.createStatement();
			//ResultSet r = state.executeQuery("select * from student");
			
			//while (r.next()){
			//	System.out.println(r.getString("last_name") + "," + r.getString("first_name"));
			//}
			
			//state.executeUpdate("INSERT into student VALUES ('new', 'person', 'M', 0)");
			
			PrintWriter out = response.getWriter();
			out.println("<HTML><BODY>");
			out.println("<H1" + "Success" + "</H1>");
			out.println("</BODY></HTML>");
			out.close();
			
			myConn.close();
		}
		catch (Exception ex)
		{
			PrintWriter out = response.getWriter();
			out.println("<HTML><BODY>");
			out.println("<H1" + "Fail" + "</H1>");
			out.println("</BODY></HTML>");
			out.close();
		}
		*/
	}

}
