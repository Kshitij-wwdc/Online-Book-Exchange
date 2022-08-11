package servlets;

import java.sql.*;
import javax.servlet.*;

import sql.Users_Const;

import java.io.*;

public class Passkey_change extends GenericServlet {
	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		String oldpwd = req.getParameter("oldpassword");
		String newpwd = req.getParameter("newpassword");
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("update users set password='" + newpwd + "' where password='" + oldpwd + "'");
			int k = ps.executeUpdate();
			if (k == 1) {
				RequestDispatcher rd = req.getRequestDispatcher("Sample.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">Password Changed Successfully</div>");
				pw.println("<div class=\"tab\"><a href=\"Sample.html\">Remove more Users</a></div>");

			} else {
				RequestDispatcher rd = req.getRequestDispatcher("Sample.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">User Not Present In The Database</div>");
				pw.println("<div class=\"tab\"><a href=\"RemoveSelf.html\">Remove more Users</a></div>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
