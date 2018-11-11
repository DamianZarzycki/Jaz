package domain;

import org.hsqldb.rights.User;
import repo.UserRepo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Login extends HttpServlet {
	static Connection con;
	static String connectionString = "jdbc:hsqldb:hsql://localhost/userDB";

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("user")!=null)
		{
			req.getRequestDispatcher("site").forward(req, resp);
		}
		else {
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	HttpSession session = req.getSession();
        System.out.println("jestesmy Login.java");
		try {
		String userName = req.getParameter("txtusername");
		String password = req.getParameter("txtpassword");

		String dbName = null;
		String dbPassword = null;

		String sql = "select * from PUBLIC.USERSDB where username=? and password=?";

		Class.forName("org.hsqldb.jdbc.JDBCDriver");

		Connection conn = DriverManager.getConnection(connectionString, "SA", "");
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1,userName);
		ps.setString(2,password);
		ResultSet rs = ps.executeQuery();
		PrintWriter out = resp.getWriter();

		while (rs.next())
		{
			dbName = rs.getString(2);
			dbPassword = rs.getString(4);
		}


		if (userName.equals(dbName) && password.equals(dbPassword))
		{
			session.setAttribute("user", userName);
			out.println("logged succesfully");
			System.out.println("jestem else ifem w Login.java");
			req.getRequestDispatcher("site").forward(req,resp);
		}
		else
		{
			System.out.println("jestem elsem w Login.java");
			req.getRequestDispatcher("/login.jsp").forward(req,resp);

		}

	} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}

}
}
