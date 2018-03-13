
// This class will help obtain student details (after logging in) to display any data they already have stored
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DynamicStudent
 */

@WebServlet("/DynamicStudent")
public class DynamicStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Connection myConn2;
	ArrayList<String> gradesStored = new ArrayList<String>();
	String idLogged = "";
	String nameLogged = "";
	String query = "";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DynamicStudent() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		// TODO Auto-generated method stub
		// response.setContentType("");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			myConn2 = DriverManager.getConnection("jdbc:mysql://mysql3000.mochahost.com/giavinh7_mark","giavinh7_default","minimumpermission123");
			//myConn2 = DriverManager.getConnection(
			//		"jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false", "default", "minimumpermission");
			Statement state = myConn2.createStatement();
			idLogged = (String)request.getAttribute("varID");
			nameLogged = (String)request.getAttribute("varName");
			
			//if (nameLogged.equals("") || nameLogged == null)
			//	query = "SELECT student_id FROM student WHERE user_name LIKE '" + nameLogged + "'";
			//else
				query = "SELECT student_id FROM student WHERE user_name LIKE '" + VerifyLogin.user + "'";

			ResultSet r = state.executeQuery(query);
			int userID = 0;
			while(r.next())
			{
				userID = r.getInt(1);
			}
			query = "SELECT * FROM grades WHERE students = " + userID;
			r = state.executeQuery(query);
			String randomString = ""; //for grade
			String randomString1 = ""; //for courses
			String randomString2 = ""; //for year
			int numberGrades = 0;
			while (r.next())
			{
				gradesStored.add(String.valueOf(r.getInt("grade")));
				randomString = randomString + String.valueOf(r.getInt("grade"));
				randomString = randomString + "`";
				randomString1 = randomString1 + String.valueOf(r.getString("course"));
				randomString1 = randomString1 + "`";
				randomString2 = randomString2 + String.valueOf(r.getString("year"));
				randomString2 = randomString2 + "`";
				numberGrades++;	
			}

			request.setAttribute("gradesList", gradesStored);
			//if (nameLogged.equals("") || nameLogged == null)
			//{
			//	request.setAttribute("name", nameLogged);
			//	request.setAttribute("personID", idLogged);
			//}
			//else
			//{
				request.setAttribute("name", VerifyLogin.user);
				request.setAttribute("personID", VerifyLogin.personID);
			//}
			request.setAttribute("test", randomString);
			request.setAttribute("courses", randomString1);
			request.setAttribute("year", randomString2);
			request.setAttribute("numGrades", numberGrades);
			//response.sendRedirect("success.jsp"); FORWARD rather than redirect if info needs to be sent
			request.getRequestDispatcher("success.jsp").forward(request, response);
			myConn2.close();
			
			
			//out.println("<HTML><BODY>");
			//out.println("<H1>" + randomString2 + "</HTML>");
			//out.close();
			
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			out.println("<HTML><BODY>");
			out.println("<H1>" + "Login failed due to database issues." + "</H1>");
			out.println("</BODY></HTML>");
			out.close();
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
