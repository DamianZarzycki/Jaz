package domain;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.HTMLEditorKit;

import repo.UserRepo;

import static domain.Login.con;
import static domain.UserAdd.connectionString;

/**
 * Servlet implementation class UserSite
 */

public class UserSite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("user")!=null)
		{
			doPost(req,res);
		}
		else {
			req.getRequestDispatcher("/index.jsp").forward(req, res);
		}
	}

	/**
     * @see HttpServlet#HttpServlet()
     */



	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession();
		res.setContentType("text/html");
		String userName = req.getParameter("txtusername");

		PrintWriter out = res.getWriter();

		res.setContentType("text/html");
		String user = (String) session.getAttribute("user");
		res.getWriter().println("<h1>Login: "+ session.getAttribute("user")+"</h1></br>");
		res.getWriter().println("\t\t<a href=\"showall\">Show all users</a>\n");
		session.setAttribute("userSite",userName);


		try {
			ArrayList<UserProfile> usersDB = UserRepo.connect(con, connectionString);

			for(UserProfile u : usersDB) {
				if(u.getUsername().equals(user) && u.isSuperUser())
				{
					out.append("<form action=\"premium.jsp\"><input type=\"submit\" value=\"Premium\"></form>");
					session.setAttribute("userPrem",userName);
				}
				if(u.getUsername().equals(user) && u.isAdmin())
				{

					session.setAttribute("userAdmin",userName);
					out.append("<form action=\"premiumMenager.jsp\"><input type=\"submit\" value=\"PremiumMenager\"></form>");
				}
			}

			out.append("<form action=\"logout\"><input type=\"submit\" value=\"Logout\"></form>");

		} catch (Exception e1) {
			e1.printStackTrace();
		}


		}
	}
