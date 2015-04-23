package net.atos.si.ma.mt.astreinte.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import net.atos.si.ma.mt.astreinte.dao.RessourceDAO;
import net.atos.si.ma.mt.astreinte.model.Ressource;
import net.atos.si.ma.mt.auth2.AuthorizationException;
import net.atos.si.ma.mt.auth2.LoginService;
import net.atos.si.ma.mt.auth2.Principale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sun.jersey.core.util.Base64;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	@Qualifier("ressourceDAOImpl")
	private RessourceDAO ressourceDAO;

	private MessageDigest messageDigest = null;

	public LoginServiceImpl() throws NoSuchAlgorithmException {
		messageDigest = MessageDigest.getInstance("MD5");

	}

	private String hashString(String string) {
		return new String(Base64.encode(messageDigest.digest(string.getBytes())));
	}

	@Override
	public Principale checkLogin(String login, String password) {
		String hashedPassword=hashString(password);
		Principale principale= ressourceDAO.checkLogin(login,hashedPassword );
		return principale;
	}

	@Override
	public Principale signup(Ressource ressource, String password,
			String confirmPassword) throws AuthorizationException {
		if (password.equals(confirmPassword))
			throw new AuthorizationException("Check confermed Password", 200);
		ressource.setPassword(hashString(password));
		ressourceDAO.saveOrUpdate(ressource);
		return new Principale(ressource.getLogin(), ressource.getRole(), null,
				-1);
	}

	@Override
	public Principale changePassword(String login, String actualPassword,
			String password, String confirmPassword)
			throws AuthorizationException {
		if (password.equals(confirmPassword))
			throw new AuthorizationException("Check confermed Password", 200);
		Principale principale = checkLogin(login, actualPassword);
		if (principale == null) {
			throw new AuthorizationException("Wrong password", 200);
		} else {
			Ressource ressource = ressourceDAO.find(principale.getId());
			ressource.setPassword(hashString(password));
			ressourceDAO.saveOrUpdate(ressource);

		}
		return principale;
	}
	

 

}
