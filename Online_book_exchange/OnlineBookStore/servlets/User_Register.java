package servlets;

import java.sql.*;
import javax.servlet.*;

import constants.IOnlineBookStoreConstants;
import sql.Users_Const;

import java.io.*;

public class User_Register extends GenericServlet {
	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType(IOnlineBookStoreConstants.CONTENT_TYPE_TEXT_HTML);

		String uName = req.getParameter(Users_Const.COLUMN_USERNAME);
		String pWord = req.getParameter(Users_Const.COLUMN_PASSWORD);
		String fName = req.getParameter(Users_Const.COLUMN_FIRSTNAME);
		String lName = req.getParameter(Users_Const.COLUMN_LASTNAME);
		String addr = req.getParameter(Users_Const.COLUMN_ADDRESS);
		String phNo = req.getParameter(Users_Const.COLUMN_PHONE);
		String mailId = req.getParameter(Users_Const.COLUMN_MAILID);
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con
					.prepareStatement("insert into " + Users_Const.TABLE_USERS + "  values(?,?,?,?,?,?,?,?)");
			ps.setString(1, uName);
			ps.setString(2, pWord);
			ps.setString(3, fName);
			ps.setString(4, lName);
			ps.setString(5, addr);
			ps.setString(6, phNo);
			ps.setString(7, mailId);
			ps.setInt(8, 2);
			int k = ps.executeUpdate();
			if (k == 1) {
				RequestDispatcher rd = req.getRequestDispatcher("Sample.html");
				rd.include(req, res);
				pw.println("<h3 class='tab'>User Registered Successfully</h3>");
			} else {
				RequestDispatcher rd = req.getRequestDispatcher("userreg");
				rd.include(req, res);
				pw.println("Registration unsuccessful. Please Retry.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
