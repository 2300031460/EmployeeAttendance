package AttendanceSystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addattendance")
public class AddEmployeeServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve parameters from the request
        String employeeId = request.getParameter("employeeid");
        String employeeName = request.getParameter("employeename");
        String attendanceDate = request.getParameter("attendancedate");
        String status = request.getParameter("status");
        String department = request.getParameter("department");

        try {
            // Load the PostgreSQL driver
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/servletemployeeattendance",
                "postgres",
                "Saketh@123"
            );

            // Check for duplicate entries (same employee, same date)
            String checkQuery = "SELECT * FROM employee_attendance WHERE employee_id = ? AND attendance_date = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkQuery);
            checkStmt.setString(1, employeeId);
            checkStmt.setString(2, attendanceDate);

            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                out.println("<h3>Attendance already marked for this employee on the given date.</h3>");
            } else {
                // Insert new attendance record
                String insertQuery = "INSERT INTO employee_attendance (employee_id, employee_name, attendance_date, status, department) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement pstmt = con.prepareStatement(insertQuery);
                pstmt.setString(1, employeeId);
                pstmt.setString(2, employeeName);
                pstmt.setString(3, attendanceDate);
                pstmt.setString(4, status);
                pstmt.setString(5, department);

                int rows = pstmt.executeUpdate();
                if (rows > 0) {
                    out.println("<h3>Attendance added successfully!</h3>");
                } else {
                    out.println("<h3>Error: Unable to add attendance.</h3>");
                }

                pstmt.close();
            }

            checkStmt.close();
            con.close();
        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
