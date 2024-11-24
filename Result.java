import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Result extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int marks1 = Integer.parseInt(request.getParameter("marks1"));
		int marks2 = Integer.parseInt(request.getParameter("marks2"));
		int marks3 = Integer.parseInt(request.getParameter("marks3"));
		try {

			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gqt_november", "root", "root");
			String s = "insert into student values(?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(s);
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setInt(3, marks1);
			pstmt.setInt(4, marks2);
			pstmt.setInt(5, marks3);
			int rows = pstmt.executeUpdate();
			PrintWriter pw = response.getWriter();
			if (rows == 1) {
				pw.println("your result is successful");
			} else {
				pw.println("failure");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
