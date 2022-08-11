package servlets;

import java.sql.*;
import javax.servlet.*;

import sql.Users_Const;

import java.io.*;

public class User_Remove extends GenericServlet {
	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		String bkid = req.getParameter("username");
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement(
					"delete from " + Users_Const.TABLE_USERS + "  where " + Users_Const.COLUMN_USERNAME + "=?");
			ps.setString(1, bkid);
			int k = ps.executeUpdate();
			if (k == 1) {
				RequestDispatcher rd = req.getRequestDispatcher("Sample.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">user Removed Successfully</div>");
				pw.println("<div class=\"tab\"><a href=\"RemoveUser.html\">Remove more Users</a></div>");

			} else {
				RequestDispatcher rd = req.getRequestDispatcher("Sample.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">User Not Present In The Database</div>");
				pw.println("<div class=\"tab\"><a href=\"RemoveUser.html\">Remove more Users</a></div>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
