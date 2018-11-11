package domain;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static domain.UserAdd.connectionString;

public class UserPremiumDel extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Poczatek UserPremiumDel.java");
        try {
            String userName = request.getParameter("txtusername");
            //String sql = "insert into PUBLIC.USERSDB (USERNAME,EMAIL,PASSWORD) values (?,?,?);";
            String sql = "UPDATE PUBLIC.USERSDB SET ISSUPERUSER=FALSE WHERE USERNAME=? ";
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            Connection conn = DriverManager.getConnection(connectionString, "SA", "");
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,userName);
            ps.executeUpdate();
            System.out.println("Usunelismy uprawnienia Premium");
            request.getRequestDispatcher("/premiumMenager.jsp").forward(request,response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}
