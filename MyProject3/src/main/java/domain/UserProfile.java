package domain;

public class UserProfile {

	public 	int id;
	public String username;
	public String password;
	public String email;
	public boolean isSuperUser;
	public boolean isAdmin;

	public UserProfile(int id, String username, String password, String email, boolean isSuperUser, boolean isAdmin) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.isSuperUser = isSuperUser;
		this.isAdmin = isAdmin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isSuperUser() {
		return isSuperUser;
	}

	public void setSuperUser(boolean superUser) {
		isSuperUser = superUser;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean admin) {
		isAdmin = admin;
	}




	
}
