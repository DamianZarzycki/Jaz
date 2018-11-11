package domain;


import repo.UserRepo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static domain.Login.con;
import static domain.UserAdd.connectionString;

public class ShowAllUsers extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user")!=null)
        {
            doPost(req,resp);
        }
        else {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

      try {
            ArrayList<UserProfile> usersDB = UserRepo.connect(con, connectionString);
          resp.getWriter().println("<div><table border=\"1\"><td> User </td><td> IsPremium</td><p>");
            for(UserProfile u : usersDB) {
               resp.getWriter().println("<tr><td>" + u.getUsername()+"</td><td>"+u.isSuperUser+"</td></tr>");
                }
            } catch (Exception e1) {
          e1.printStackTrace();
      }




    }
}
