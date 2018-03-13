
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addGrade
 */
@WebServlet("/AddGrade")
public class addGrade extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addGrade() {
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
			String grade = request.getParameter("gradeInput");
			String course = request.getParameter("courseInput");
			String year = request.getParameter("yearInput");
			//String id = String.valueOf(VerifyLogin.personID);
			String id = request.getParameter("id");
		// TODO Auto-generated method stub
		try {
			response.setContentType("text/html");
			Class.forName("com.mysql.jdbc.Driver");
			Connection myConn = DriverManager.getConnection("jdbc:mysql://mysql3000.mochahost.com:3306/giavinh7_mark","giavinh7_default","minimumpermission123");
			Statement state = myConn.createStatement();
			String query = "INSERT INTO grades VALUES ('" + id + "', '" + course + "', '" + grade + "', '" + year + "')";
			out.println(query);
			state.executeUpdate(query);
			response.sendRedirect("DynamicStudent");

		} catch (Exception ex) {
			out.println("<HTML><BODY>");
			out.println("<H1>" + "Login failed due to server or database issues." + id + "</H1>");
			out.println("</BODY></HTML>");
			out.close();
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
