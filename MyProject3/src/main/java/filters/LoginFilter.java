package filters;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
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

/**
 * Servlet Filter implementation class LoginFilter
 */
//WebFilter("/login")
public class LoginFilter implements Filter {

    static Connection con;
    static String connectionString = "jdbc:hsqldb:hsql://localhost/userDB";

    public LoginFilter() {

    }

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("jestesmy w loginFilter");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();




        if (session != null && session.getAttribute("user") != null) {
            res.sendRedirect("profile.jsp");
        }

        chain.doFilter(request, response);

            }

        public void init (FilterConfig fConfig) throws ServletException {

        }

    }
