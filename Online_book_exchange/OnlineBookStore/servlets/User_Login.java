package servlets;

import javax.servlet.*;

import constants.IOnlineBookStoreConstants;
import sql.Users_Const;

import java.io.*;
import java.sql.*;

public class User_Login extends GenericServlet {
	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType(IOnlineBookStoreConstants.CONTENT_TYPE_TEXT_HTML);
		String uName = req.getParameter(Users_Const.COLUMN_USERNAME);
		String pWord = req.getParameter(Users_Const.COLUMN_PASSWORD);
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + Users_Const.TABLE_USERS + " WHERE "
					+ Users_Const.COLUMN_USERNAME + "=? AND " + Users_Const.COLUMN_PASSWORD + "=? AND " + Users_Const.COLUMN_USERTYPE + "=2");
			ps.setString(1, uName);
			ps.setString(2, pWord);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				RequestDispatcher rd = req.getRequestDispatcher("Sample.html");
				rd.include(req, res);
				pw.println("<div class=\"home hd brown\">Welcome, " + uName + "!</div><br/>");
				pw.println("<div class=\"tab hd brown\">User Login Successful !</div><br/>");
				pw.println("<div class=\"tab\"><a href=\"viewbook\">VIEW BOOKS</a></div>");
				pw.println("<div class='tab'><a href=\"buybook\">BUY BOOKS</a></div>");
				pw.println("<div class='tab'><a href=\"RemoveSelf.html\">Remove Self</a></div>");
				pw.println("<div class='tab'><a href=\"changepassword.html\">Change Password</a></div>");
			} else {

				RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">Incorrect user credentials</div>");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}