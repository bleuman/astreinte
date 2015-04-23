package net.atos.si.ma.mt.auth2;

import net.atos.si.ma.mt.astreinte.model.Ressource;

public interface LoginService {

	Principale checkLogin(String login, String password);

	Principale signup(Ressource ressource, String password,
			String confirmPassword) throws AuthorizationException;

	Principale changePassword(String login, String actualPassword,
			String password, String confirmPassword)
			throws AuthorizationException;

}
