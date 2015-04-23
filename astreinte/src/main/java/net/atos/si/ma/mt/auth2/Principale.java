package net.atos.si.ma.mt.auth2;

public class Principale {

	private long id;
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

	public Principale() {
	}

	public Principale(String login, String role, String token, long id) {
		this.login = login;
		this.role = role;
		this.token = token;
		this.id = id;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
