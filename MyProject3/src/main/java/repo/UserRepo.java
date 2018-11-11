package repo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import domain.UserProfile;

public class UserRepo {

	/*static Connection con;

	static String connectionString = "jdbc:hsqldb:hsql://localhost/userDB";
*/

	public static ArrayList<UserProfile> connect(Connection con, String connectionString) throws Exception {

		System.out.println("Attempting to connect to db ...");

		ArrayList<UserProfile> usersDB = new ArrayList<>();

		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


		try {
			try {

				con = DriverManager.getConnection(connectionString, "SA", "");


				if (con != null) {
					System.out.println("Connected!");
				} else {
					System.out.println("Problem with creating connection");
				}

				PreparedStatement pst = con.prepareStatement("SELECT * FROM PUBLIC.USERSDB");
				pst.clearParameters();
				ResultSet rs = pst.executeQuery();

				while (rs.next()) {
					usersDB.add(new UserProfile(
									rs.getInt(1),
									rs.getString(2),
									rs.getString(3),
									rs.getString(4),
									rs.getBoolean(5),
									rs.getBoolean(6)
							)
					);
				}
				}catch(SQLException e){
					e.printStackTrace();
				}
				} finally {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return usersDB;
		}
	}

