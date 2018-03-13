
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;

/**
 * Servlet implementation class deleteGrade
 */
@WebServlet("/DeleteGrade")
public class deleteGrade extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public deleteGrade() {
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
		String course = request.getParameter("varName");
		String id = request.getParameter("varID");
		Connection myConn;
		
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			myConn = DriverManager.getConnection("jdbc:mysql://mysql3000.mochahost.com:3306/giavinh7_mark","giavinh7_default","minimumpermission123");
			Statement state = myConn.createStatement();
			//state.executeUpdate("INSERT INTO grades VALUES ('2', 's', '80', '3'");
			String query = "DELETE FROM grades WHERE students = " + id + " AND course LIKE '" + course + "';";
			//String query = "DELETE FROM grades WHERE students = 5";
			//String query = "INSERT INTO grades VALUES ('" + "5" + "', '" + "ff" + "', '" + "57" + "', '" + "1" + "')";
			
			state.executeUpdate(query);
			myConn.close();

			/*
			 * when you send http request from ajax, it means that you are sending the 
			 * request in separate thread and not in the main thread (the page itself 
			 * from where you are sending the request). So redirection at the servlet 
			 * will not reflect at the client end. In order to achieve this, send back 
			 * the URL to which you want to redirect as a response to request and on 
			 * success method of ajax simply use java script window.location(URL);
			 */
			
			/*
			JSONObject jobj = new JSONObject();
			response.setContentType("application/json");
			String redirect = "DynamicStudent";
			jobj.put("url",redirect);
			response.getWriter().write(jobj.toString());
			*/
			

		} catch (Exception ex) {
			response.setContentType("text/html");
			out.println("<HTML><HEAD></HEAD><BODY>");
			out.println("<H1>" + "Login failed due to server or database issues." + id + "divide" + course + "</H1>");
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
