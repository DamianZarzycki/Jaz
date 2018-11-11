package domain;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.enterprise.inject.New;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import repo.UserRepo;



public class UserAdd extends HttpServlet{
	static Connection con;

	static String connectionString = "jdbc:hsqldb:hsql://localhost/userDB";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


        System.out.println("Poczatek UserAdd");
        try {
            String userName = req.getParameter("txtusername");
            String userEmial = req.getParameter("txtemail");
            String password = req.getParameter("txtpassword");

            String sql = "insert into PUBLIC.USERSDB (USERNAME,EMAIL,PASSWORD) values (?,?,?);";
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            Connection conn = DriverManager.getConnection(connectionString, "SA", "");
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,userName);
            ps.setString(2,userEmial);
            ps.setString(3,password);
            ps.executeUpdate();
            System.out.println("Dodalismy do bazy");
            req.getRequestDispatcher("/login.jsp").forward(req,res);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
		
	}
	


