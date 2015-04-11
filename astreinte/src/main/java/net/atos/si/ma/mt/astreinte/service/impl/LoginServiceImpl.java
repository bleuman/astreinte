package net.atos.si.ma.mt.astreinte.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import net.atos.si.ma.mt.astreinte.dao.RessourceDAO;
import net.atos.si.ma.mt.auth2.LoginService;

public class LoginServiceImpl implements LoginService{

	@Autowired
	@Qualifier("ressourceDAOImpl")
	private RessourceDAO ressourceDAO;
	public String checkLogin(String login, String password) {
		// TODO Auto-generated method stub
		return ressourceDAO.checkLogin(login, password);
	}

}
