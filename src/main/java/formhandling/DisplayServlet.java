package formhandling;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/displayAttendance")
public class DisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Instead of doPost() & doGet(), you can write your logic in service() method
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("Service Method is Initialized<br>");
		out.println("<b>Employee Attendance Details</b><br>");

		String employeeId = request.getParameter("id");
		String employeeName = request.getParameter("name");
		String attendanceStatus = request.getParameter("attendance");

		out.println("Employee ID: " + employeeId + "<br>");
		out.println("Employee Name: " + employeeName + "<br>");
		out.println("Attendance Status: " + attendanceStatus + "<br>");
	}
}
