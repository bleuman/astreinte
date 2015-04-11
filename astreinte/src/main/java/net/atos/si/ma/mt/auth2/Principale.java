package net.atos.si.ma.mt.auth2;

public class Principale {

	private String login;
	
	private String role;
	
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getLogin() {
		return login;
	}

	
	public Principale(String login, String role,String token) {
		this.login = login;
		this.role = role;
		this.token = token;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
