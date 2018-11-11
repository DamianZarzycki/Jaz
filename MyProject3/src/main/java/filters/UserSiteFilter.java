package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/site")
public class UserSiteFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        System.out.println("jestesmy w filtrze usersite");
        if(session.getAttribute("user")==null)
        {
            System.out.println("jestesmy ifie ");
            req.getRequestDispatcher("/login.jsp").forward(req, res);
        }
        else{
            req.getRequestDispatcher("site").forward(req, res);
        chain.doFilter(req, res);}
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
