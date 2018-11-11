package filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.el.StaticFieldELResolver;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.UserProfile;
import repo.UserRepo;

/**
 * Servlet Filter implementation class RegistrationFilter
 */
@WebFilter("/add")
public class RegistrationFilter implements Filter {

    static Connection con;
    static String connectionString = "jdbc:hsqldb:hsql://localhost/userDB";

	public void destroy() {
	
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String userName = req.getParameter("txtusername");
        String userEmial = req.getParameter("txtemail");
        String password = req.getParameter("txtpassword");
        String confpassword = req.getParameter("textCpassword");

        try {
            List<UserProfile> usersDB = UserRepo.connect(con, connectionString);
            for(UserProfile up : usersDB)
                if(up.username.equals(userName) || up.email.equals(userEmial)){
                    req.getRequestDispatcher("/index.jsp").forward(req,res);
                }else if (!password.equals(confpassword)){
                    System.out.println("jestesmy w ifie filtra rejestracji");
                    req.getRequestDispatcher("/index.jsp").forward(req,res);
                }
                else {
                    req.getRequestDispatcher("/login.jsp").forward(req,res);
                    chain.doFilter(req, res);
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public void init(FilterConfig fConfig) throws ServletException { 
	
	}

}
