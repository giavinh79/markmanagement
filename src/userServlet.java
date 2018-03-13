import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

// Extend HttpServlet class to create Http Servlet
@SuppressWarnings("serial")
@WebServlet("/UserServlet")
public class userServlet extends HttpServlet {
	public Connection myConn1;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("username1");
		String pass = request.getParameter("password1");
		String address = request.getParameter("address");
		PrintWriter out = response.getWriter(); 
		String query = "f";
		int maxID = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			myConn1 = DriverManager.getConnection("jdbc:mysql://mysql3000.mochahost.com:3306/giavinh7_mark","giavinh7_default","minimumpermission123");
			Statement state = myConn1.createStatement();
			//$max = SELECT MAX( customer_id ) FROM customers;
			query = "SELECT * FROM student WHERE user_name LIKE " + "'" + user + "'";
			ResultSet r = state.executeQuery(query);
			if (r.isBeforeFirst()) {
				query = "bleh";
				out.println("<HTML><BODY>");
				out.println("<H1>" + "Login failed as there is already an existing user with that username." + "</H1>");
				out.println("</BODY></HTML>");
			} else {
				ResultSet id = state.executeQuery("SELECT MAX(student_id) FROM student");
				
				if ( id.next() ){
					   maxID = id.getInt(1); 
				} else {
					maxID = 0;
				}

				query = "INSERT INTO student VALUES ('" + user + "'" + ", " + "'" + pass + "', " + "'" + address + "', " + "'"+ (maxID+1) + "'" + ")";
				state.executeUpdate(query);
				myConn1.close();
				response.sendRedirect("index.html");
			}
			out.close();
			myConn1.close();

		} catch (Exception ex) {
			// TODO Auto-generated catch block
			out.println("<HTML><BODY>");
			out.println("<H1>" + "Login failed due to server issues." + query + "</H1>");
			out.println("</BODY></HTML>");
			out.close();
		} finally {
			/*try {
				myConn.close();
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}*/
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void destroy() {
		/*
		 * leaving empty for now this can be used when we want to do something
		 * at the end of Servlet life cycle
		 */
	}
}